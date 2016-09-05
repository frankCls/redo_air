window.addEventListener("load", function() {
  var countries = document.getElementsByTagNameNS("http://www.w3.org/2000/svg", "path");

  /* loop through all rects in the navigation */
  for (var i = 0; i < countries.length; i++) {
    /* do the hovering */
	  countries[i].addEventListener("mouseon",
      function (event) {
        //blue2red(event.target, 0);
      }, false);
	  countries[i].addEventListener("mouseout",
      function (event) {
        //red2blue(event.target, 0);
      }, false);
  }
}, false);

/* change the color from blue to red */
function blue2red(element, red) {
  red = Number(red) || 0;
  red += 5;
  element.style.setProperty("fill", "rgb("+
                            String(red)+", 0, "+String(255-red)+")", null);
  if (red < 255) {
    window.setTimeout(function () {
      blue2red(element, red);
    }, 10);
  }
};