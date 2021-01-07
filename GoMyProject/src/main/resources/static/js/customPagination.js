//Returns an array of maxLength (or less) page numbers
//where a 0 in the returned array denotes a gap in the series.
//Parameters:
//totalPages:     total number of pages
//page:           current page
//maxLength:      maximum size of returned array 27 4 5
function getPageList(totalPages, page, maxLength) {
 if (maxLength < 5) throw "maxLength must be at least 5";

 function range(start, end) {
     return Array.from(Array(end - start + 1), (_, i) => i + start); 
 }

 var sideWidth = maxLength < 9 ? 1 : 2;//1
 var leftWidth = (maxLength - sideWidth*2 - 3) >> 1;
 var rightWidth = (maxLength - sideWidth*2 - 2) >> 1;
 

 if (totalPages <= maxLength) {
     // no breaks in list
     return range(1, totalPages);
 }
 // 1<=5-1-1-0 true
 if (page <= maxLength - sideWidth - 1 - rightWidth) {
     // no break on left of page
     return range(1, maxLength - sideWidth - 1)
         .concat(0, range(totalPages - sideWidth + 1, totalPages));
 }
 if (page >= totalPages - sideWidth - 1 - rightWidth) {
     // no break on right of page
     return range(1, sideWidth)
         .concat(0, range(totalPages - sideWidth - 1 - rightWidth - leftWidth, totalPages));
 }
 // Breaks on both sides
 return range(1, sideWidth)
     .concat(0, range(page - leftWidth, page + rightWidth),
             0, range(totalPages - sideWidth + 1, totalPages));
}

//Below is an example use of the above function.
$(function () {
 // Number of items and limits the number of items per page
 var numberOfItems = $('#totalPage').val()-1;
 var limitPerPage = 1;
 // Total pages rounded upwards
 var totalPages = Math.ceil(numberOfItems / limitPerPage);
 totalPages=parseInt(totalPages);
 // Number of buttons at the top, not counting prev/next,
 // but including the dotted buttons.
 // Must be at least 5:
 var paginationSize = 5; 
 var currentPage;
 var records=$('#record').val();
 var sortField=$('#sortField').val();
 var sortDir=$('#sortDir').val();
 var linkPrefix=$('#linkPrefix').val();
 function showPage(whichPage) {
     if (whichPage < 1 || whichPage > totalPages) return false;
     currentPage = whichPage;
  
     // Replace the navigation items (not prev/next):            
     $(".pagination li").slice(1, -1).remove();
     getPageList(totalPages, currentPage, paginationSize).forEach( item => {
         $("<li>").addClass("page-item")
                  .addClass(item ? "current-page" : "disabled")
                  .toggleClass("active", item == currentPage).append(
             $("<a>").addClass("page-link").attr({
                 href: linkPrefix+"/page/"+item+"?sortField="+sortField+"&sortDir="+sortDir+"&records="+records}).text(item || "...")
         ).insertBefore("#next-page");
     });
     // Disable prev/next when at first/last page:
     $("#previous-page").toggleClass("disabled", currentPage == 1);
     $("#next-page").toggleClass("disabled", currentPage == totalPages);
     return true;
 }

 var currentPage2=$('#currentPage').val(); 
 // Include the prev/next buttons:

	 
 $(".pagination").append(
     $("<li>").addClass("page-item").attr({ id: "previous-page" }).append(
         $("<a>").addClass("page-link").attr({
             href: linkPrefix+"/page/"+(parseInt(currentPage2)-1)+"?sortField="+sortField+"&sortDir="+sortDir+"&records="+records}).text("Prev")
     ),
     $("<li>").addClass("page-item").attr({ id: "next-page" }).append(
         $("<a>").addClass("page-link").attr({
             href: linkPrefix+"/page/"+(parseInt(currentPage2)+1)+"?sortField="+sortField+"&sortDir="+sortDir+"&records="+records}).text("Next")
     )
 );


 showPage(parseInt(currentPage2));

 // Use event delegation, as these items are recreated later    
 $(document).on("click", ".pagination li.current-page:not(.active)", function () {
     return showPage(+$(this).text());
 });

});