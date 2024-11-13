document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('quizPostUpdateForm');
    const quizBoardId = document.getElementById("quizBoardId").value;
    const id = document.getElementById("postId").value;

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const formData = new FormData(form);
        const data = {
            quizTitle: document.getElementById("title").value,
            hintContent: document.getElementById("content").value
        };

        console.log(data);

        const apiUrl = `/api/quizBoard/${quizBoardId}/quizPost/${id}`;

        try {
            const response = await fetch(apiUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                window.location.href = `/quizBoard/${quizBoardId}/quizPost/${id}`;
            } else {
                console.error('Failed to update the post.');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
});