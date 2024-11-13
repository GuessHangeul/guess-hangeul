document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('generalPostUpdateForm');
    const generalBoardId = form.dataset.generalBoardId;
    const id = form.dataset.postId;

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const formData = new FormData(form);
        const data = {
            title: formData.get('title'),
            content: formData.get('content'),
            isHidden: formData.get('isHidden') === 'on',
            view: parseInt(formData.get('view'), 10)
        };

        const actionUrl = `/generalBoard/${generalBoardId}/updatePost/${id}`;

        try {
            const response = await fetch(actionUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                window.location.href = `/generalBoard/${generalBoardId}`;
            } else {
                console.error('Failed to update the post.');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
});