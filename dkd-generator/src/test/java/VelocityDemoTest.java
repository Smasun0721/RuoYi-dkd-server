import com.dkd.generator.util.VelocityInitializer;
import com.dkd.generator.util.VelocityUtils;
import domain.Region;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class VelocityDemoTest {
    public static void main(String[] args) throws IOException {
        // 1.初始化模板
        VelocityInitializer.initVelocity();
        // 2.准备数据模型
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("message", "加油同学！");
        // 2.1 添加数据对象
        Region region1=new Region(1L,"上海");
        Region region2=new Region(2L,"北京");
        Region region3=new Region(3L,"广州");
        Region region4=new Region(4L,"深圳");
        Region region5=new Region(5L,"杭州");
        Region region6=new Region(6L,"西安");
        Region region7=new Region(7L,"武汉");
        Region region8=new Region(8L,"南京");
        Region region9=new Region(9L,"苏州");
        Region region10=new Region(10L,"厦门");
        List<Region> regionList= List.of(region1,region2,region3,region4,region5,region6,region7,region8,region9,region10);
        velocityContext.put("regionList", regionList);
        velocityContext.put("region", region1);
        // 3.读取模板
        Template Template = Velocity.getTemplate("vm/index.html.vm", "UTF-8");
        // 4.合并模板和数据模型
        FileWriter fileWriter = new FileWriter("C:\\Users\\Smasun\\Desktop\\index.html");
        Template.merge(velocityContext, fileWriter);
        fileWriter.close();
    }
}
