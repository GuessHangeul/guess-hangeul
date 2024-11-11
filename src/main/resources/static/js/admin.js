// 유저 삭제
const deleteButtons = document.querySelectorAll('.delete-btn');

deleteButtons.forEach((button, index) => {
    console.log(`Button ${index + 1} 등록 완료`); // 등록 여부 확인
    button.addEventListener('click', event => {
        console.log('버튼 클릭됨'); // 버튼 클릭 시 확인
        const userId = button.closest('tr').querySelector('.userId').textContent.trim();
        console.log(`userId: ${userId}`); // 가져온 userId 확인

        fetch(`/api/users/${userId}`, {
            method: 'PUT'
        })
            .then(response => {
                if (!response.ok) throw new Error("삭제 실패");
                alert('삭제 완료되었습니다.');
                location.replace('/admin');
            })
            .catch(error => {
                console.error('삭제 중 오류 발생:', error);
                alert('삭제 실패했습니다.');
            });
    });
});

// 닉네임 초기화
const resetButtons = document.querySelectorAll('.reset-btn');

resetButtons.forEach((button, index) => {
    console.log(`Button ${index + 1} 등록 완료`); // 등록 여부 확인
    button.addEventListener('click', event => {
        console.log('버튼 클릭됨'); // 버튼 클릭 시 확인
        const userId = button.closest('tr').querySelector('.userId').textContent.trim();
        console.log(`userId: ${userId}`); // 가져온 userId 확인

        fetch(`/api/admin/initializeNickname/${userId}`, {
            method: 'GET'
        })
            .then(response => {
                if (!response.ok) throw new Error("초기화 실패");
                alert('초기화가 완료되었습니다.');
                location.replace('/admin');
            })
            .catch(error => {
                console.error('초기화 중 오류 발생:', error);
                alert('초기화에 실패했습니다.');
            });
    });
});

// 정렬
function sortTable(columnIndex) {
    const table = document.getElementById("userTable");
    const tbody = table.querySelector("tbody");
    const rows = Array.from(tbody.querySelectorAll("tr"));
    let ascending = true;

    // 기존 정렬 방향 확인
    if (table.getAttribute("data-sorted-column") == columnIndex) {
        ascending = table.getAttribute("data-sort-direction") !== "asc";
    }

    // 정렬 기준 열의 데이터를 기준으로 정렬
    rows.sort((rowA, rowB) => {
        const cellA = rowA.cells[columnIndex].textContent.trim();
        const cellB = rowB.cells[columnIndex].textContent.trim();

        if (!isNaN(cellA) && !isNaN(cellB)) {
            return ascending ? cellA - cellB : cellB - cellA;
        }
        return ascending
            ? cellA.localeCompare(cellB)
            : cellB.localeCompare(cellA);
    });

    // 정렬된 행을 테이블에 다시 추가
    tbody.innerHTML = "";
    rows.forEach(row => tbody.appendChild(row));

    // 정렬 방향 및 정렬된 열 인덱스 저장
    table.setAttribute("data-sorted-column", columnIndex);
    table.setAttribute("data-sort-direction", ascending ? "asc" : "desc");
}

