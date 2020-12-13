package cn.itcast.elasticsearch.text;

import cn.itcast.elasticsearch.ItemRepository;
import cn.itcast.elasticsearch.pojo.Item;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Elasticsearch {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void test() {
        elasticsearchTemplate.createIndex(Item.class);
        elasticsearchTemplate.putMapping(Item.class);
    }

    @Test
    public void createTest() {
       /* Item item = new Item(1L, "小米手机7", "手机",
                "小米", 2999.9, "http:image.leyou.com/123456.jpg");
         itemRepository.save(item);
         */
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg")); // 接收对象集合，实现批量新增

        itemRepository.saveAll(list);
    }

    @Test
    public void selectTest(){
        //Optional<Item> item = itemRepository.findById(2L);
        Iterable<Item> price = itemRepository.findAll(Sort.by("price").descending());
        for (Item item : price) {
            System.out.println(item);
        }
    }

    @Test
    public void selectByTitleTest(){
        //Optional<Item> item = itemRepository.findById(2L);
        List<Item> items = itemRepository.findByTitle("手机");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void selectByPriceBetweenTest(){
        //Optional<Item> item = itemRepository.findById(2L);
        List<Item> items = itemRepository.findByPriceBetween(2999D,3700D);
        for (Item item : items) {
            System.out.println(item);
        }
    }


    @Test
    public void searchTest(){
        //通过查询构建器工具构建查询条件
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "小米");
        FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("title", "mete");
        Iterable<Item> items = itemRepository.search(fuzzyQuery);
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void NativeTest(){
        //构建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加基本查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("category","手机"));
        //添加分页条件
        //queryBuilder.withPageable(PageRequest.of(1,3));  //page从0开始的

        //添加排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));

        //执行查询，获取分页结果集
        Page<Item> itemPage = itemRepository.search(queryBuilder.build());
        System.out.println(itemPage.getTotalElements());  //总条数
        System.out.println(itemPage.getTotalPages());  //总页数
        itemPage.forEach(System.out::println);
    }

    @Test
    public  void AggsTest(){
        //1.初始化自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //2.添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("BrandAggs").field("brand"));

        //3.添加结果集过滤，不包括任何字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(
                new String[]{},null));

        //4.执行聚合查询
        AggregatedPage<Item> itemPage = (AggregatedPage<Item>) itemRepository.search(queryBuilder.build());

        //5.解析聚合结果集,根据聚合的类型以及字段类型要进行强转，brand-字符串，聚合类型-词条聚合
        //brandAggs通过聚合名称获取聚合对象
        StringTerms brandAggs = (StringTerms) itemPage.getAggregation("BrandAggs");

        //6.获取聚合桶
        List<StringTerms.Bucket> buckets = brandAggs.getBuckets();

        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
        });
    }
    @Test
    public void AggsSubTest(){
        //1.初始化自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //2.添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("BrandAggs").field("brand").subAggregation(
                AggregationBuilders.avg("price_avg").field("price")
        ));

        //3.添加结果集过滤，不包括任何字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(
                new String[]{},null));

        //4.执行聚合查询
        AggregatedPage<Item> itemPage = (AggregatedPage<Item>) itemRepository.search(queryBuilder.build());

        //5.解析聚合结果集,根据聚合的类型以及字段类型要进行强转，brand-字符串，聚合类型-词条聚合
        //brandAggs通过聚合名称获取聚合对象
        StringTerms brandAggs = (StringTerms) itemPage.getAggregation("BrandAggs");

        //6.获取聚合桶
        List<StringTerms.Bucket> buckets = brandAggs.getBuckets();

        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            Map<String, Aggregation> aggregationMap = bucket.getAggregations().asMap();
            InternalAvg price_avg = (InternalAvg)aggregationMap.get("price_avg");
            System.out.println(price_avg.getValue());

        });
    }
}
