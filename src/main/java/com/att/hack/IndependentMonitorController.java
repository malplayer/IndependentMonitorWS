package com.att.hack;

import com.twilio.sdk.resource.factory.SmsFactory;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;

import com.twilio.sdk.resource.instance.Sms;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;

import java.util.HashMap;
import java.util.List;import java.util.Map;

/**
 * Created by malcolm on 2/21/14.
 */
@Controller
@RequestMapping("/steps")
public class IndependentMonitorController {

    private static final String API_KEY="2FB790E05BDFB76B980C044589907305";
    private static final String EIA_URL="http://api.eia.gov/series/?api_key={0}&series_id=ELEC.PRICE.{1}-COM.A";

    public static final String ACCOUNT_SID = "AC28ac8f23a75a5802b1ce56c325295dc2";
    public static final String AUTH_TOKEN = "f527a93cd99bd1658513960d564a9be7";
    public static final String PHONE_NUMBER= "4694252540";


    //private static Map<String,String> stateMap = new HashMap<String,String>();

    @RequestMapping(value="{alert}", method = RequestMethod.GET)
    public String getFruit(@PathVariable String lightName, ModelMap model) {

        Alert alert = new Alert("malcolm","wtf");
        model.addAttribute("model", alert);
        return "alert";

    }

    @RequestMapping(method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public ModelAndView getModel(ModelAndView model) {
        model.setViewName("alert");

        Alert alert = new Alert("malcolm","wtf");
        model.addObject("model", alert);

        return model;
    }


    @RequestMapping(value = "/sendtrigger", method = RequestMethod.POST, produces="application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String getTrigger(HttpServletRequest httpServletRequest) throws TwilioRestException {


        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = httpServletRequest.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }


     String trigger = jb.toString();



        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build a filter for the SmsList
        Map<String, String> params = new HashMap<String, String>();
        params.put("Body", "There was an alert from the Independent Monitor Service");
        params.put("To", "9728373515");
        params.put("From", PHONE_NUMBER);

        SmsFactory messageFactory = client.getAccount().getSmsFactory();
        Sms message = messageFactory.create(params);
        System.out.println("MessageID:\t"+message.getSid());

       System.out.println("Trigger:\t" + trigger);


        return trigger;
    }


}
