package com.hoon.cmd.controller.image;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoon on 2016-03-01.
 */
@Slf4j
@Controller
public class ImageController {
    @Value("${image.home}")
    private String imageHome;

    @RequestMapping("/image/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("fileName") String fileName) throws IOException {
        log.debug("imageHome : " + imageHome);
        log.debug("fileName : " + fileName);
        File file = new File(imageHome + File.separator + fileName + ".jpg");
        InputStream in = FileUtils.openInputStream(file);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
    }

    @RequestMapping("/image/gallery")
    public ModelAndView gallery() {
        ModelAndView modelAndView = new ModelAndView("image/gallery");

        List<String> imageList = new ArrayList<>();
        imageList.add("/image/smile_1438956250972");
        imageList.add("/image/jordan");
        imageList.add("/image/papa_1432348910105");

        modelAndView.addObject("imageList", imageList);
        return modelAndView;
    }
}
