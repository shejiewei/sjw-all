package com.sjw.control;

import com.sjw.utils.HiSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shejiewei on 2019/3/19.
 */
@Controller
public class HiControl {
   @Autowired
    private HiSender hiSender;
    @RequestMapping("/hi")
    public void hi(){

       hiSender.send();

    }

}
