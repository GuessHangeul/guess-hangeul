// const deleteButton = document.getElementById('delete-btn');
//
// if (deleteButton) {
//     deleteButton.addEventListener('click', event => {
//         let userId = document.getElementById('userId').value;
//         fetch(``, {
//             method: 'DELETE'
//         }).then(() => {
//             alert('삭제가 완료되었습니다');
//             location.replace('/admin');
//         });
//     });
// }

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

