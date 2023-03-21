package com.example.demo.controllers;

import com.example.demo.ShapeDrawer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ShapeController {

    @RequestMapping(
            value =  "/showImg",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] index(@RequestParam (required = false) String shape, String size, String colour, Boolean fill) throws IOException, NoSuchFieldException, IllegalAccessException {
        ShapeDrawer drawingObject = new ShapeDrawer(shape, size, colour, fill);
        File t = drawingObject.Img2file(drawingObject.printer());

        if (t != null){
            InputStream in = new FileInputStream(t);
            return in.readAllBytes();
        }
       return null;
    }

    @RequestMapping(path = "/downloadImg", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> download(@RequestParam (required = false) String shape, String size, String colour, Boolean fill) throws IOException, NoSuchFieldException, IllegalAccessException {
        ShapeDrawer drawingObject = new ShapeDrawer(shape, size, colour, fill);
        File t = drawingObject.Img2file(drawingObject.printer());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(t));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(t.length());
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg");
        return new HttpEntity<byte[]>(resource.getInputStream().readAllBytes(), headers);
    }
}
