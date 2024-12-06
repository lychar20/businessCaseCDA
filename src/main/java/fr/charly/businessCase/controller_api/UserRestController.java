package fr.charly.businessCase.controller_api;


import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.DTO.UserDTO;
import fr.charly.businessCase.DTO.UserUpdateDTO;
import fr.charly.businessCase.entity.User;
import fr.charly.businessCase.json_views.JsonViews;
import fr.charly.businessCase.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @PostMapping
    @JsonView(JsonViews.UserShowView.class)
    public User create(@Valid @RequestBody UserDTO dto) {
        return userService.create(dto);
    }

    @PutMapping("/{uuid}")
    @JsonView(JsonViews.UserShowView.class)
    public User update(@Valid @RequestBody UserUpdateDTO dto, @PathVariable String uuid) {
        return userService.update(dto, uuid);
    }

    @GetMapping("/activate/{code}")
    @JsonView(JsonViews.UserShowView.class)
    public User activate(@PathVariable String code) {
        return userService.activate(code);
    }

    @GetMapping("/{uuid}")
    @JsonView(JsonViews.UserShowView.class)
    public User show(@PathVariable String uuid) {
        return userService.findOneById(uuid);
    }

//    @DeleteMapping("/{uuid}")
//    public Boolean delete(@PathVariable String uuid) {
//        return userService.delete(uuid);
//    }

}
