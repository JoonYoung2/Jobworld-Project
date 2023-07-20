function personalCheck() {
	var title = document.getElementById('title').value;
	var email = document.getElementById('email').value;
	var phone = document.getElementById('phone').value;
	var zip = document.getElementById('sample6_postcode').value;
	var detail = document.getElementById('sample6_detailAddress').value;
	console.log(email);
//	console.log(email);
//	console.log(birth);
//	console.log(phone);
//	console.log(zip);
//	console.log(detail);
	
	if (title === ""){
		alert("제목 입력은 필수입니다.");
		document.getElementById("title").focus();
		return;
	}
	
	if (email === "") {
		alert("이메일 입력은 필수입니다.");
		//        document.getElementById("msg").innerText="이메일 입력은 필수입니다.";
		document.getElementById("email").focus();
		return;
	}
	if (phone === "") {
		alert("핸드폰 입력은 필수입니다.");
		//        document.getElementById("msg").innerText="핸드폰 입력은 필수입니다.";
		document.getElementById("phone").focus();
		return;
	}
	if (zip === "") {
		alert("우편번호 입력은 필수입니다.");
		//        document.getElementById("msg").innerText="우편번호 입력은 필수입니다.";
		document.getElementById("sample6_postcode").focus();
		return;
	}
	if (detail === "") {
		alert("상세주소 입력은 필수입니다.");
		//        document.getElementById("msg").innerText="상세주소 입력은 필수입니다.";
		document.getElementById("sample6_detailAddress").focus();
		return;
	}

	var emailRegex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	var phoneRegex = /^[0-9]{11,11}$/;

	var emailr = emailRegex.test(email);
	var phoner = phoneRegex.test(phone);

	if (emailr === false) {
		alert("이메일이 형식에 맞지 않습니다.");
		document.getElementById("email").focus();
		//        document.getElementById("msg").innerText="이메일이 형식에 맞지 않습니다.";
		return;
	}
	if (phoner === false) {
		alert("핸드폰번호가 형식에 맞지 않습니다.");
		document.getElementById("phone").focus();
		//        document.getElementById("msg").innerText="핸드폰번호가 형식에 맞지 않습니다.";
		return;
	}

	alert("인적사항이 저장되었습니다.");
	document.getElementById('personalInfo').submit();
}

const fileSelect = () => {
	document.getElementById('file').click();
}

const previewImage = (event) => {
	var input = event.target;
	var reader = new FileReader();

	reader.onload = function() {
		var imgElement = document.getElementById('preview');
		imgElement.src = reader.result;
	};

	reader.readAsDataURL(input.files[0]);

	document.getElementById('preview').style.display = 'block';
	document.getElementById('userImgFile').style.display = 'none';
}

const fileCancel = () => {
	document.getElementById('cancelButton').click();
	document.getElementById('preview').style.display = 'none';
	document.getElementById('userImgFile').style.display = 'block';
}

const addressInputClick = () => {
	document.getElementById('codeZip').click();
}