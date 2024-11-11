// 게시판 삭제

function deleteBoard(deleteButton) {
    // 삭제 요청 URL 설정
    const boardId = deleteButton.getAttribute("data-boardId");
    console.log(boardId);
    const deleteUrl = `/api/admin/deleteBoard/generalBoard/${boardId}`;

    fetch(`/api/admin/deleteBoard/generalBoard/${boardId}`, {
        method: 'GET'
    }).then(() => {
        alert('삭제가 완료되었습니다');
        location.reload();
    });
}

// 게시판 버튼 클릭 시 /admin/quizBoard/{boardId} id 변경
function redirectToBoard(button) {
    const boardId = button.getAttribute("data-boardId");

    // 필요 시 리다이렉트
    if (boardId) {
        window.location.href = `/admin/generalBoard/${boardId}`;
    } else {
        console.error("Board ID is missing");
    }
}

// 게시글 숨기기
document.querySelector('.hide-btn').addEventListener('click', function () {
    // 선택된 게시글의 ID를 수집
    const selectedIds = Array.from(document.querySelectorAll('.post-checkbox:checked'))
        .map(checkbox => checkbox.value);

    // 선택된 게시글이 있는지 확인
    if (selectedIds.length === 0) {
        alert("숨길 게시글을 선택하세요.");
        return;
    }

    const postElement = document.querySelector('.post');
    const boardId = postElement.getAttribute('data-boardId');

    // API 요청 URL 생성 (GET 요청에 선택된 postId 포함)
    const url = `/api/admin/generalBoard/${boardId}/post/changeVisibilityHide?` +
        selectedIds.map(id => `postId=${id}`).join('&');

    // GET 요청 전송
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.length > 0) {
                alert("선택한 게시글이 숨겨졌습니다.");
                location.reload();  // 페이지를 새로고침하여 업데이트
            } else {
                alert("게시글 숨기기에 실패했습니다.");
            }
        })
        .catch(error => console.error('Error:', error));
});


// 게시글 숨기기 해제
document.querySelector('.unhide-btn').addEventListener('click', function () {
    // 선택된 게시글의 ID를 수집
    const selectedIds = Array.from(document.querySelectorAll('.post-checkbox:checked'))
        .map(checkbox => checkbox.value);

    // 선택된 게시글이 있는지 확인
    if (selectedIds.length === 0) {
        alert("숨길 게시글을 선택하세요.");
        return;
    }

    const postElement = document.querySelector('.post');
    const boardId = postElement.getAttribute('data-boardId');

    // API 요청 URL 생성 (GET 요청에 선택된 postId 포함)
    const url = `/api/admin/generalBoard/${boardId}/post/changeVisibilityUnhidden?` +
        selectedIds.map(id => `postId=${id}`).join('&');

    // GET 요청 전송
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.length > 0) {
                alert("선택한 게시글이 숨김 해제되었습니다.");
                location.reload();  // 페이지를 새로고침하여 업데이트
            } else {
                alert("게시글 숨기기 해제에 실패했습니다.");
            }
        })
        .catch(error => console.error('Error:', error));
});

// 게시글 삭제
document.querySelector('.delete-btn').addEventListener('click', function () {
    // 선택된 게시글의 ID를 수집
    const selectedIds = Array.from(document.querySelectorAll('.post-checkbox:checked'))
        .map(checkbox => checkbox.value);

    // 선택된 게시글이 있는지 확인
    if (selectedIds.length === 0) {
        alert("삭제할 게시글을 선택하세요.");
        return;
    }

    const postElement = document.querySelector('.post');
    const boardId = postElement.getAttribute('data-boardId');
    const url = `/api/generalBoard/${boardId}/generalPost?` +
        selectedIds.map(id => `postId=${id}`).join('&');

    // GET 요청 전송
    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                alert("선택한 게시글이 삭제되었습니다.");
                location.reload();  // 페이지를 새로고침하여 업데이트
            } else {
                alert("게시글 삭제에 실패했습니다.");
            }
        })
        .catch(error => console.error('Error:', error));
});

// 검색
function searchPost(event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음
    const generalBoardId = postElement.getAttribute('data-boardId');
    const searchText = document.getElementById('searchText').value; // 입력된 검색어 가져오기

    // 검색어를 포함한 URL로 이동 (여기서 title은 검색어)
    window.location.href = `/api/generalBoard/${generalBoardId}/generalPost?search=${searchText}`;
}