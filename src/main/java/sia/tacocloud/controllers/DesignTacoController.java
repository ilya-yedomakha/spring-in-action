package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.domain.Ingredient;
import sia.tacocloud.domain.Order;
import sia.tacocloud.domain.Taco;
import sia.tacocloud.domain.User;
import sia.tacocloud.repositories.IngredientRepository;
import sia.tacocloud.repositories.TacoRepository;
import sia.tacocloud.repositories.UserRepository;


import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@Slf4j
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private final TacoRepository designRepo;

    private final UserRepository userRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model, Principal principal) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredientRepo.findAll().forEach(ingredients::add);
//        Ingredient.Type[] types = Ingredient.Type.values();
//        for (Ingredient.Type type : types) {
//            model.addAttribute(type.toString().toLowerCase(),
//                    filterByType(ingredients, type));
//        }
        return "design";
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("taco")Taco design, Errors errors,
                                @ModelAttribute("order") Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        Taco savedTaco = designRepo.save(design);
        order.addDesign(savedTaco);
//        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

}
