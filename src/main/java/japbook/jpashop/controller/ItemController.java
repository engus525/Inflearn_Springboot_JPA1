package japbook.jpashop.controller;

import jakarta.validation.Valid;
import japbook.jpashop.domain.item.Book;
import japbook.jpashop.domain.item.Item;
import japbook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController
{
    private final ItemService itemService;

    @GetMapping("items/new")
    public String createForm(Model model)
    {
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "items/new")
    public String create(@Valid @ModelAttribute("form") BookForm form, BindingResult result)
    {
        //BindingResult -> @Valid 실패 후 처리 로직
        if(result.hasErrors()) return "items/createItemForm";

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("items")
    public String list(Model model)
    {
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,Model model)
    {
        Book book = (Book) itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setName(book.getName());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getStockQuantity());
        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());

        model.addAttribute("form",form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,@ModelAttribute("form") BookForm form)
    {
        Book book = new Book();
        /*DB에 한 번 다녀옴으로써 식별자가 있는 entity를 "준영속 엔티티"라고 한다.
        * 준영속 엔티티는, JPA에서 관리하지 않는다. 즉! 수정을 해도 jpa가 알지 못해 DB 반영이 어렵다.
        * 그럼 어떻게 수정할까?
        * 1. 변경 감지 기능 사용
        * -> 영속성 컨텍스트에서 엔티티를 다시 조회하여 데이터를 수정한다.
        *    transaction commit 시점에 변경 감지(dirty checking)가 동작한다. -> update sql 실행
        * 2. merge 사용(여기서 사용한 방식)
        * -> 준영속 엔티티를 영속 상태로 변환한다.
        *    1. merge()실행 후, param으로 넘어온 준영속 엔티티의 식별자 값으로 엔티티를 조회한다.
        *    2. 조회한 영속 엔티티에 값을 채워넣는다.
        *    3. 영속 엔티티를 반환한다.
        *
        * !!주의!!
        * 변경 감지 기능은 수정한 값만을 반영한다.
        * 그러나 merge는 모든 값을 변경하므로, param에 지정되지 않은 값은 null 처리될 수 있다.
        * 따라서 실무에서는 변경 감지 기능을 사용한다.
        *
        * */
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//        itemService.saveItem(book);

        //권장 방법
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());


        return "redirect:/items";
    }
}
