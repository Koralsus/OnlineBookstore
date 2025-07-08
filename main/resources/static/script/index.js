document.addEventListener("DOMContentLoaded", () => {
  fetch("http://localhost:8080/api/books/best-sellers?page=0&size=5")
    .then(res => res.json())
    .then(books => {
      const list = document.getElementById("best-sellers");
      list.innerHTML = "";
      books.content.forEach(book => {
        list.innerHTML += `
          <div class="col-md-3">
            <div class="card mb-4 shadow-sm">
              <div class="card-body">
                <h5 class="card-title">${book.title}</h5>
                <p class="card-text">by ${book.author}</p>
                <p class="card-text">₱${book.price}</p>
                <a href="catalog.html?isbn=${book.isbn}" class="btn btn-primary">View</a>
              </div>
            </div>
          </div>`;
      });
    })
    .catch(error => console.error("Error loading best sellers:", error));
});
