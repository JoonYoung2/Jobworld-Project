function newWindow(recruitId){
			var popupWidth = 1000;
			var popupHeight = 1000;
			var popupX = (window.screen.width / 2) - (popupWidth / 2);
			var popupY= (window.screen.height / 2) - (popupHeight / 2);
			window.open("recruitInfo.go?recruit_id=" + recruitId, "JOBWORLD", 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
		}