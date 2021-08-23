let index = {
    init: function () {
        $("#btn-save").on("click", () => { //function(){}, () => {} this 를 바인딩하기 위해서
            this.save();
        });  //btn-save 를 클릭하면 save 함수가 호출됨

        $("#btn-delete").on("click", () => { //function(){}, () => {} this 를 바인딩하기 위해서
            this.deleteById();
        });

        $("#btn-update").on("click", () => { //function(){}, () => {} this 를 바인딩하기 위해서
            this.update();
        });

        $("#btn-reply-save").on("click", () => { //function(){}, () => {} this 를 바인딩하기 위해서
            this.replySave();
        });
    },

    save: function () {
        //alert('user의 save함수 호출됨.')
        let data = {
            title: $("#title").val(), //#id값으로 찾고 그에 대한 value를 변수에 바인딩
            content: $("#content").val()
        };

        $.ajax({
            type:"POST",
            url : "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            //정상
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        }) //ajax 통신을 이용해서 3개의 데이터를 Json으로 변경하여 insert 요청
    },

    deleteById: function () {
        let id = $("#id").text();

        $.ajax({
            type:"DELETE",
            url : "/api/board/"+id,
            dataType : "json"
        }).done(function(resp){
            //정상
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        })
    },

    update: function () {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(), //#id값으로 찾고 그에 대한 value를 변수에 바인딩
            content: $("#content").val()
        };

        $.ajax({
            type:"PUT",
            url : "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            //정상
            alert("글수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        })
    },

    replySave: function () {
        //alert('user의 save함수 호출됨.')
        let data = {
            userId :$("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        };

        $.ajax({
            type:"POST",
            url : '/api/board/' + data.boardId +'/reply',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            //정상
            alert("댓글 작성이 완료되었습니다.");
            location.href = '/board/' + data.boardId;
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        }) //ajax 통신을 이용해서 3개의 데이터를 Json으로 변경하여 insert 요청
    },

    replyDelete: function (boardId, replyId) {
        $.ajax({
            type:"DELETE",
            url : '/api/board/'+boardId +'/reply/' + replyId,
            dataType : "json"
        }).done(function(resp){
            //정상
            alert("댓글 삭제 성공");
            location.href = '/board/' + boardId;
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        }) //ajax 통신을 이용해서 3개의 데이터를 Json으로 변경하여 insert 요청
    },
}


index.init(); //index의 init 함수 호출