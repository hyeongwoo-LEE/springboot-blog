package com.cos.blog.controller.api;


import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDTO;
import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDTO<Integer> save(@RequestBody Board board,
                                     @AuthenticationPrincipal PrincipalDetail principal){

        boardService.글쓰기(board, principal.getUser());

        return new ResponseDTO<>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDTO<Integer> deleteById(@PathVariable int id){
        boardService.글삭제하기(id);

        return new ResponseDTO<>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDTO<Integer> update(@PathVariable int id, @RequestBody Board board){

        boardService.글수정하기(id, board);

        return new ResponseDTO<>(HttpStatus.OK.value(),1);
    }


    //데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
    //dto를 사용하지 않는 이유는 -> 프로젝트 규모가 크지 않아서 -> 실제로는 사용해야함.
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDTO<Integer> replySave(@RequestBody ReplySaveRequestDTO replySaveRequestDTO){

        boardService.댓글쓰기(replySaveRequestDTO);

        return new ResponseDTO<>(HttpStatus.OK.value(),1);
    }


}
