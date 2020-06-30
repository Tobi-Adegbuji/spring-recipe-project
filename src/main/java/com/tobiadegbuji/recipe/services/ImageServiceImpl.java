package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.domain.Recipe;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
            try {
                Recipe recipe = recipeRepository.findById(recipeId).get();


                //Converting primitive byte array to wrapper byte array (hibernate recommendation)
                Byte[] byteObjects = new Byte[file.getBytes().length];
                int i = 0;
                for (byte b : file.getBytes()) {
                    byteObjects[i++] = b;
                }


                recipe.setImage(byteObjects);

                recipeRepository.save(recipe);
            }
            catch (IOException e){
                //todo handle exception
                log.error("Error occured", e);
                e.printStackTrace();
            }
    }
}
