var seconds = 3;
var foo;
function redirect() {
	document.location.href = 'booking.jsf';
}

function updateSecs() {

	if (document.getElementById("seconds") != null) {
		document.getElementById("seconds").innerHTML = seconds;
		seconds--;
		if (seconds == -1) {
			clearInterval(foo);
			redirect();
		}
	}
}

function countdownTimer() {

	foo = setInterval(function() {
		updateSecs()
	}, 1000);

}

countdownTimer();