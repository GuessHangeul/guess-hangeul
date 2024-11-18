document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('generalPostUpdateForm');
    const generalBoardId = document.getElementById("generalBoardId").value;
    const id = document.getElementById("postId").value;

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const formData = new FormData(form);
        const data = {
            title: document.getElementById("title").value,
            content: document.getElementById("content").value
        };

        console.log(data);

        const apiUrl = `/api/generalBoard/${generalBoardId}/generalPost/${id}`;

        try {
            const response = await fetch(apiUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                window.location.href = `/generalBoard/${generalBoardId}/generalPost/${id}`;
            } else {
                console.error('Failed to update the post.');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
});