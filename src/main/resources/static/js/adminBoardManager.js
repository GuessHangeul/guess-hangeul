// 승급
const applyButtons = document.querySelectorAll('.apply-btn');

applyButtons.forEach((button, index) => {
    console.log(`Button ${index + 1} 등록 완료`); // 등록 여부 확인
    button.addEventListener('click', event => {
        console.log('버튼 클릭됨'); // 버튼 클릭 시 확인
        const userId = button.closest('tr').querySelector('.boardManagerId').textContent.trim();
        console.log(`boardManagerId: ${userId}`); // 가져온 userId 확인

        fetch(`/api/admin/acceptBoardManager?boardManagerId`, {
            method: 'PUT', // PUT 방식 설정
            headers: {
                'Content-Type': 'application/json' // JSON 형식으로 전송
            },
            body: JSON.stringify(userId) // 선택된 ID 리스트를 JSON으로 변환하여 RequestBody로 전송
        })
            .then(response => {
                if (!response.ok) throw new Error("승인 실패");
                alert('승인 완료되었습니다.');
                location.replace('/admin/boardManagerApply');
            })
            .catch(error => {
                console.error('승인 중 오류 발생:', error);
                alert('승인 실패했습니다.');
            });
    });
});

// 반려
const rejectButtons = document.querySelectorAll('.reject-btn');

rejectButtons.forEach((button, index) => {
    console.log(`Button ${index + 1} 등록 완료`); // 등록 여부 확인
    button.addEventListener('click', event => {
        console.log('버튼 클릭됨'); // 버튼 클릭 시 확인
        const userId = button.closest('tr').querySelector('.boardManagerId').textContent.trim();
        console.log(`boardManagerId: ${userId}`); // 가져온 userId 확인

        fetch(`/api/admin/rejectBoardManager`, {
            method: 'PUT', // PUT 방식 설정
            headers: {
                'Content-Type': 'application/json' // JSON 형식으로 전송
            },
            body: JSON.stringify(userId) // 선택된 ID 리스트를 JSON으로 변환하여 RequestBody로 전송
        })
            .then(response => {
                if (!response.ok) throw new Error("반려 실패");
                alert('반려 완료되었습니다.');
                location.replace('/admin/boardManagerApply');
            })
            .catch(error => {
                console.error('반려 중 오류 발생:', error);
                alert('반려 실패했습니다.');
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