function newWindow(recruitId) {
	var popupWidth = 1000;
	var popupHeight = 1000;
	var popupX = (window.screen.width / 2) - (popupWidth / 2);
	var popupY = (window.screen.height / 2) - (popupHeight / 2);
	window.open("recruitInfo.go?recruit_id=" + recruitId, "JOBWORLD", 'status=no, height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY);
}

function loginWindow() {
	var popupWidth = 500;
	var popupHeight = 300;
	var popupX = (window.screen.width / 2) - (popupWidth / 2);
	var popupY = (window.screen.height / 2) - (popupHeight / 2);
	window.open("/member/login", "UserLogin", 'status=no, height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY);
}

function companyLoginWindow() {
	var popupWidth = 1000;
	var popupHeight = 1000;
	var popupX = (window.screen.width / 2) - (popupWidth / 2);
	var popupY = (window.screen.height / 2) - (popupHeight / 2);
	window.open("companyLogin", "CompanyLogin", 'status=no, height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY);
}

function registerWindow(){
	var popupWidth = 500;
	var popupHeight = 800;
	var popupX = (window.screen.width / 2) - (popupWidth / 2);
	var popupY = (window.screen.height / 2) - (popupHeight / 2);
	window.open("/member/register", "UserRegister", 'status=no, height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY);
}

function applyWindow(recruitId){
			var popupWidth = 550;
			var popupHeight = 470;
			var popupX = (window.screen.width / 2) - (popupWidth / 2);
			var popupY= (window.screen.height / 2) - (popupHeight / 2);
			window.open("apply?recruit_id=" + recruitId, "USERINFO", 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}