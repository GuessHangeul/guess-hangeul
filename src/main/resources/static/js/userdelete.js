
function UserSoftDelete(button) {
    const deleteButton = document.getElementById('delete-btn');
    //소프트 삭제 이므로 isDeleted만 변경
    if (deleteButton) {
        let params = new URLSearchParams(location.search);
        let id = params.get('userId');

        fetch(`api/users/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({isDeleted: true})
        }).then(() => {
            alert('사용자가 삭제 되었습니다.');
            location.replace(`/api/users/`);
        });
    }
}
