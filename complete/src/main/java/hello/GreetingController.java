package hello;

import com.google.api.services.samples.youtube.cmdline.data.UploadVideo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

@Controller
public class GreetingController {


    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public  String handleFileUpload( Model model,@RequestParam("name") String name,@RequestParam("file") MultipartFile file){
        Greeting greeting=new Greeting();
        File dddr=null;
        dddr=new File(Greeting.class.getResource(".").getPath());
        greeting.setId(1222);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File f = new File(dddr.getParent()+"/static/",name);
                greeting.setContent(f.getCanonicalPath());
                greeting.setName(name);
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(f));
                stream.write(bytes);
                stream.close();
                model.addAttribute("greeting", greeting);
                UploadVideo uploadVideo=new UploadVideo();
                //uploadVideo.uploadVideotoYT(name,f.getAbsolutePath(),"This is titile");
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
        System.out.println("\n\n4\n\n");
        return "listpage";
    }



    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @RequestMapping(value="/greeting", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }

}
