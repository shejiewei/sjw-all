package flink_spring;

/**
 * Created by shejiewei on 2020/5/11.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UrlInfo {
    private int id;

    private String url;

    private String hash;
}