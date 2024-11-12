function searchTitle(boardType, boardId) {
    var searchInput = document.getElementById("search-input");
    if (searchInput) {
        if (boardType === 1) {
            window.location.href = `/generalBoard/${boardId}?search=${searchInput.value}`;
        } else {
            window.location.href = `/quizBoard/${boardId}?search=${searchInput.value}`;
        }
    }
}