let index = {
    init: function () {
        $("#btn-save").on("click", () => { //function(){}, () => {} this 를 바인딩하기 위해서
            this.save();
        });  //btn-save 를 클릭하면 save 함수가 호출됨

        $("#btn-update").on("click", () => { //function(){}, () => {} this 를 바인딩하기 위해서
            this.update();
        });
    },

    save: function () {
        //alert('user의 save함수 호출됨.')
        let data = {
            username: $("#username").val(), //#id값으로 찾고 그에 대한 value를 변수에 바인딩
            password: $("#password").val(),
            email: $("#email").val()
        };
        //console.log(data);
        //ajax 호출시 default가 비동기 호출
        //ajax가 통신을 성공하고 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요.
        $.ajax({
            //회원가입 수행 요청
            type:"POST",
            url : "/auth/joinProc",
            data: JSON.stringify(data), //그냥 data를 보내면 javaScript 오브젝트를 보내는 것 -> 이해x
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            //dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) -> javascript 오브젝트로 변경 -> default로 바뀜
        }).done(function(resp){ //pending 끝난 후 실행
            if(resp.status === 500){
                alert("회원가입에 실패하였습니다.");
            }else{
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }

        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해서 3개의 데이터를 Json으로 변경하여 insert 요청
    },

    update: function () {
        //alert('user의 save함수 호출됨.')
        let data = {
            id : $("#id").val(),
            username : $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            //회원가입 수행 요청
            type:"PUT",
            url : "/user",
            data: JSON.stringify(data), //그냥 data를 보내면 javaScript 오브젝트를 보내는 것 -> 이해x
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            //dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) -> javascript 오브젝트로 변경 -> default로 바뀜
        }).done(function(resp){ //pending 끝난 후 실행
            //정상
            alert("회원수정이 완료되었습니다.");
            console.log(resp);
            location.href = "/";
        }).fail(function(error){
            //실패
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해서 3개의 데이터를 Json으로 변경하여 insert 요청
    }
}

index.init(); //index의 init 함수 호출