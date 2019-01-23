package 注解.V1;
/**
 * Created by shejiewei on 2019/1/23.
 */
public class CelebrateController {

    @MethodLog(method="celebrate birthday",operator="we")
    public void celebrateBirthday(){

    }

    @MethodLog(operator = "we")
    public void celebrateNewYears(){

    }

    @MethodLog(method="celebrate harvest")
    public void celebrateHarvest(){

    }

    @MethodLog
    public void yeah(){

    }
}