/**
 * 
 */
//alert("hie");
var cameras=document.getElementById("cameras");
var laptops=document.getElementById("laptops");
var mobilephone=document.getElementById("mobilephone");
var tablets=document.getElementById("tablets");
var video=document.getElementById("video");

var reviewsdiv =document.getElementById("reviews");
var noreviews=document.getElementById("noreviews");

function camera()
{
	//alert("hie");
	var mycode="cameras <span class='caret'></span>";
	document.getElementById("pro").value="David Busch's Canon EOS Rebel T3/1100D Guide to Digital SLR Photography";
	document.getElementById("productname").className="visible";
	document.getElementById("reviews").className="visible";
	document.getElementById("noreviews").className="hidden";
	document.getElementById("detail").innerHTML=mycode;
	document.getElementById("review1").innerHTML="While the T3 is a pretty easy camera to use, there are a lot of hidden features and tricks I would probably never have discovered without this book. Chapter 3 starts out with...\"One thing that surprises new owners of the Canon EOS Rebel T3 is that the camera has a total of 496 buttons, dials, switches, levers, latches, and knobs bristling from it's surface. Okay, I lied. Actually, the real number is closer to two dozen controls and adjustments, but that's still a lot of components to master, especially when you consider that many of these controls serve double-duty to give you access to multiple functions.\"This quote serves two purposes here; to illustrate that this is not just a dry reference manual and to point out that there are in fact quite a few bells and whistles that the camera has to offer. I've actually found this book to be an enjoyable read; however I also spend a lot of my time studying military standards which can make just about anything else seem interesting! While it may seem time consuming to read a book geared to just one camera, it's actually a time saver if you really want to know how to get the most out of your T3.";
	document.getElementById("review2").innerHTML="This is the latest of Busch's best-of-breed guidebooks, offering a comprehensive manual for the Canon EOS Rebel T3 entry-level digital SLR. The T3 is a basic 12-megapixel model that lacks some features and the high price tag of more luxe beginner cameras like the T3i, but its affordability will attract beginners who want a camera with room to grow into.  Busch's book has the information you need to learn and grow, supplying easy explanations of using the T3's basic features accompanied by simple introductions to the kind of more advanced capabilities that will help even the rawest neophyte take the best pictures of their lives. Although very accessible, this book goes much deeper than the run-of-the-mill camera guides to offer advice on how to use every control, select every menu option.. The author's friendly style leads you through the camera's features and tells you not only what each capability does, but gives examples of when you'll get the most benefit from using it. Busch explains all the new features for the D5100 in a way that's easy to understand. His novel approach is to condense all the essential information about each and every control on the camera into a multi-page \"roadmap\" that explains their functions without the need to leap around from page to page, as is the case with the Canon manual. He devotes several full chapters to outlining every menu option, and actually provides much more detail than you'll find in most of the other \"expanded\" guides for the T3.Although this book is comprehensive, it's easy reading, and if you want a condensed version to carry around with you, Busch offers that too. His David Busch's Compact Field Guide for the Canon EOS Rebel T3/1100D is a worthy replacement for those laminated cheat sheets that fold out into six index card sized tabs with one-sentence summaries of most of the menu settings, too. While the portable version duplicates some of the information found in this book, the two make a great pair for home and field. You'll want to buy them both to have the information you need on the go and for study at home.";
	document.getElementById("review3").innerHTML="The book is easy to understand and put together well for an amateur getting into Photography.  I like how the basic information is given first and the details are in later chapters.  It is comprehensive and gives instruction on how to reprogram your camera to meet your personal needs and desires.  I will keep this book as a reference for my camera";
	

}

function lap()
{
	//alert("hie");
	document.getElementById("pro").value="";

	var mycode="Laptops <span class='caret'></span>";
	document.getElementById("detail").innerHTML=mycode;
	document.getElementById("productname").className="visible";


	document.getElementById("reviews").className="visible";
	document.getElementById("noreviews").className="hidden";
	document.getElementById("review1").innerHTML="hello review1 of laptop";
	document.getElementById("review2").innerHTML="hello review2 of laptop";
	document.getElementById("review3").innerHTML="hello review3 of laptop";
	

}

function mob()
{
	//alert("hie");
	document.getElementById("pro").value="Sony CCD Quartz Wall Clock Security Spy Hidden Camera";

	var mycode="Mobile Phones <span class='caret'></span>";
	document.getElementById("detail").innerHTML=mycode;
	document.getElementById("productname").className="visible";


	document.getElementById("reviews").className="visible";
	document.getElementById("noreviews").className="hidden";
	document.getElementById("review1").innerHTML="hello review1 of mobilephone";
	document.getElementById("review2").innerHTML="hello review2 of mobilephone";
	document.getElementById("review3").innerHTML="hello review3 of mobilephone";
	

}

function tab()
{
	//alert("hie");
	document.getElementById("pro").value="Sony CCD Quartz Wall Clock Security Spy Hidden Camera";

	var mycode="Tablets <span class='caret'></span>";
	document.getElementById("detail").innerHTML=mycode;
	document.getElementById("productname").className="visible";

	document.getElementById("reviews").className="visible";
	document.getElementById("noreviews").className="hidden";
	document.getElementById("review1").innerHTML="hello review1 of tablet";
	document.getElementById("review2").innerHTML="hello review2 of tablet";
	document.getElementById("review3").innerHTML="hello review3 of tablet";
	

}

function tvs()
{
	//alert("hie");
	document.getElementById("pro").value="Sony CCD Quartz Wall Clock Security Spy Hidden Camera";

	var mycode="TV's <span class='caret'></span>";
	document.getElementById("detail").innerHTML=mycode;
	document.getElementById("productname").className="visible";

	document.getElementById("reviews").className="visible";
	document.getElementById("noreviews").className="hidden";
	document.getElementById("review1").innerHTML="hello review1 of tvs";
	document.getElementById("review2").innerHTML="hello review2 of tvs";
	document.getElementById("review3").innerHTML="hello review3 of tvs";
	

}

function vid()
{
	//alert("hie");
	document.getElementById("pro").value="Sony CCD Quartz Wall Clock Security Spy Hidden Camera";

	var mycode="Video Surveillance <span class='caret'></span>";
	document.getElementById("detail").innerHTML=mycode;
	document.getElementById("productname").className="visible";


	document.getElementById("reviews").className="visible";
	document.getElementById("noreviews").className="hidden";
	document.getElementById("review1").innerHTML="hello review1 of video";
	document.getElementById("review2").innerHTML="hello review2 of video";
	document.getElementById("review3").innerHTML="hello review3 of video";
	

}


