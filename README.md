# web-filter
#### 项目介绍

　　用于前后端交互过程中参数过滤

#### 过滤类型

1. Emoji（FilterType.Emoji）
2. 字符串替换（FilterType.Replace）


#### 配置

```java
    import java.util.Map;@SpringBootApplication
    public class Application {
        @Bean
        public FilterPointcutAdvisor filterPointcutAdvisor() {
            // 设置需要扫描的接口包路径，可以设置多个
            FilterPointcutAdvisor advisor = new FilterPointcutAdvisor("path1","path2")
            // includePatterns：手动设置路径匹配规则
            // excludePatterns: 手动设置路径排除规则
            .addFilter(FilterType.Emoji.newInstance().includePatterns("/**").excludePatterns(""))
            // 构造函数需传递参数，需要进行替换的字符串模板，java.util.Map类型
            .addFilter(FilterType.Replace.newInstance(java.util.Map).includePatterns("/**").excludePatterns(""))
            ;
        }
    }
```
