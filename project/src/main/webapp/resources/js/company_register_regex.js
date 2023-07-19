function id_click(){
    document.getElementById("member_id").innerText="★ 영문자 또는 숫자 6~20 ★";
}
function id_blur(){
    document.getElementById("member_id").innerText="";
}

function pw_click(){
    document.getElementById("member_pw").innerText="★ 8~20자 영문, 숫자, 특수문자 필수 ★";
}
function pw_blur(){
    document.getElementById("member_pw").innerText="";
}

function check(){
    var id = document.getElementById('id').value;
    var pw = document.getElementById('pw').value;
    var pwCheck = document.getElementById('pwCheck').value;
    var nm = document.getElementById('nm').value;
    var business = document.getElementById('business').value;
    var emplNum = document.getElementById('emplNum').value;
    var size = document.getElementById('size').value;
    var site = document.getElementById('site').value;
    var file = document.getElementById('file').value;
    if(id == ""){
		alert("아이디 입력은 필수입니다.");
//        document.getElementById("msg").innerText="아이디 입력은 필수입니다.";
        document.getElementById("id").focus();
        return;
    }
    if(pw == ""){
		alert("비밀번호 입력은 필수입니다.");
//        document.getElementById("msg").innerText="비밀번호 입력은 필수입니다.";
        document.getElementById("pw").focus();
        return;
    }
    if(nm == ""){
		alert("회사명 입력은 필수입니다.");
//        document.getElementById("msg").innerText="이름 입력은 필수입니다.";
        document.getElementById("nm").focus();
        return;
    }
    if(business == ""){
		alert("업종 입력은 필수입니다.");
//        document.getElementById("msg").innerText="이메일 입력은 필수입니다.";
        document.getElementById("business").focus();
        return;
    }
    if(emplNum == ""){
		alert(" 사원수 입력은 필수입니다.");
//        document.getElementById("msg").innerText="생년월일 입력은 필수입니다.";
        document.getElementById("emplNum").focus();
        return;
    }
    if(size == ""){
		alert("회사 규모 입력은 필수입니다.");
//        document.getElementById("msg").innerText="핸드폰 입력은 필수입니다.";
        document.getElementById("size").focus();
        return;
    }
    if(site == ""){
		alert("회사 사이트 입력은 필수입니다.");
//        document.getElementById("msg").innerText="우편번호 입력은 필수입니다.";
        document.getElementById("site").focus();
        return;
    }
    if(file == ""){
		alert("상세주소 입력은 필수입니다.");
//        document.getElementById("msg").innerText="상세주소 입력은 필수입니다.";
        document.getElementById("file").focus();
        return;
    }
    if(pw != pwCheck){
		alert("비밀번호가 일치하지 않습니다.");
//        document.getElementById("msg").innerText="비밀번호가 일치하지 않습니다.";
        document.getElementById("pw").focus();
        return;
    }
    
    var idRegex = /^[A-Za-z]{1}[A-Za-z0-9_-]{5,19}$/;
    var pwRegex = /(?=.*\d)(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,19}/;
    var emplNumRegex = /^[0-9]$/;
    
    var idr = idRegex.test(id);
    var pwr = pwRegex.test(pw);
    var emplNumr = emplNumRegex.test(emplNum);
    

    if(idr === false){
		alert("아이디가 형식에 맞지 않습니다.");
		document.getElementById("id").focus();
//        document.getElementById("msg").innerText="아이디가 형식에 맞지 않습니다.";
        return;
    }
    if(pwr === false){
		alert("비밀번호가 형식에 맞지 않습니다.");
		document.getElementById("pw").focus();
//        document.getElementById("msg").innerText="비밀번호가 형식에 맞지 않습니다.";
        return;
    }
    if(emplNumr <= 0){
		alert("사원수가 형식에 맞지 않습니다.");
		document.getElementById("emplNum").focus();
//        document.getElementById("msg").innerText="생년월일이 형식에 맞지 않습니다.";
        return;
    }

    document.getElementById('f').submit();
}