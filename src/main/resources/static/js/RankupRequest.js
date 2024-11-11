function approve(button) {
    const approveButton = document.getElementById('approve-btn');//윤허하다 버튼에 작용
    if (approveButton) {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`api/boardManagerApply/${id}`, {
            method: 'PUT',
            headers: {"Content-Type": "application/json",},
            body: JSON.stringify({status: 1})
        }).then(() => {
            alert('집현전 입직을 윤허 하였다.');
            location.replace(`/api/boardManagerApply`);
        });
    }
}
function rejected(button) {
    const rejectButton = document.getElementById('reject-btn');//윤허하다 버튼에 작용
    if (rejectButton) {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`api/boardManagerApply/${id}`, {
            method: 'PUT',
            headers: {"Content-Type": "application/json",},
            body: JSON.stringify({status: -1})
        }).then(() => {
            alert('허나 윤허 하지 않았다.');
            location.replace(`/api/boardManagerApply`);
        });
    }
}