package com.tobiadegbuji.recipe.controllers;

import com.tobiadegbuji.recipe.commands.RecipeCommand;
import com.tobiadegbuji.recipe.services.ImageService;
import com.tobiadegbuji.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{id}/image")
    public String postImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile multipartFile){
        imageService.saveImageFile(Long.parseLong(id), multipartFile);
        return "redirect:/recipe/" + id + "/show";
    }

    @PostMapping("recipe/{id}/newimage")
    public String postImageNew(@PathVariable String id, @RequestParam("imagefile") MultipartFile multipartFile, Model model){
        imageService.saveImageFile(Long.parseLong(id), multipartFile);
        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/newrecipeform2";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void getRecipeImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(id));
        byte[] byteArray = new byte[recipeCommand.getImage().length];
        int i = 0;
        for(Byte wrapperByte: recipeCommand.getImage()){
            byteArray[i++] = wrapperByte;
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }


}
