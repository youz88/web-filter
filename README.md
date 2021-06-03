# web-filter
** 项目介绍
　　用于前后端交互过程中参数过滤
###### 配置

```java
    @SpringBootApplication
    public class Application {
        @Bean
        public FilterPointcutAdvisor filterPointcutAdvisor() {
            FilterPointcutAdvisor advisor = new FilterPointcutAdvisor("扫描的")
                    .addFilter(FilterType.Emoji.newInstance().includePatterns("").excludePathPatterns(""))
                    .addFilter(FilterType.Replace.newInstance(new HashMap(){{put("a","11111111");}}).includePatterns("").excludePathPatterns(""))
                    ;
        }
    }
```
    