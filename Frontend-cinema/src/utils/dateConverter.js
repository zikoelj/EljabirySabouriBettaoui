const dateConvert = (date) => {
    return new Date(date).toLocaleDateString("fr", {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });
}

export default dateConvert;