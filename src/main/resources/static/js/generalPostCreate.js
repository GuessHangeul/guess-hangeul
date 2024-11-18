document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('generalPostForm');

    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        const formData = new FormData(form);
        const data = getFormData(formData);

        try {
            const response = await submitForm(form.action, data);
            handleResponse(response);
        } catch (error) {
            console.error('Error:', error);
        }
    });
});

function getFormData(formData) {
    return {
        title: formData.get('title'),
        content: formData.get('content'),
        isHidden: formData.get('isHidden') === 'on'
    };
}

async function submitForm(actionUrl, data) {
    return await fetch(actionUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

function handleResponse(response) {
    if (response.ok) {
        window.location.href = `/generalBoard/${generalBoardId}`;
    } else {
        console.error('Failed to create the general post.');
    }
}