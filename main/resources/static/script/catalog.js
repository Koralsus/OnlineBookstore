document.addEventListener("DOMContentLoaded", () => {
  loadCategories();
  loadBooks();

  const searchBtn = document.getElementById("searchBtn");
  if (searchBtn) {
    searchBtn.addEventListener("click", searchAndFilterBooks);
  }
});

function loadCategories() {
  fetch("http://localhost:8080/api/categories")
    .then(res => res.json())
    .then(categories => {
      const categorySelect = document.getElementById("categorySelect");
      if (!categorySelect) return;
      categories.forEach(cat => {
        const option = document.createElement("option");
        option.value = cat.categoryId;
        option.textContent = cat.name;
        categorySelect.appendChild(option);
      });
    })
    .catch(error => console.error("Error loading categories:", error));
}

function loadBooks() {
  fetch("http://localhost:8080/api/books/all")
    .then(res => res.json())
    .then(data => showBooks(data.content))
    .catch(error => console.error("Error loading books:", error));
}

function searchAndFilterBooks() {
  const keyword = document.getElementById("searchInput").value;
  const categoryId = document.getElementById("categorySelect").value;

  let url = "http://localhost:8080/api/books/search/author-title?query=" + encodeURIComponent(keyword);
  if (categoryId) {
    url += "&categoryId=" + encodeURIComponent(categoryId);
  }

  fetch(url)
    .then(res => res.json())
    .then(data => showBooks(data.content))
    .catch(error => console.error("Error searching books:", error));
}

function showBooks(books) {
  const list = document.getElementById("catalog-list");
  if (!list) return;
  list.innerHTML = "";

  if (!books || books.length === 0) {
    list.innerHTML = "<p>No books found.</p>";
    return;
  }

  books.forEach(book => {
    const div = document.createElement("div");
    div.className = "col-md-3";
    div.innerHTML = `
      <div class="card mb-4 shadow-sm book-card">
        <img src="${book.imageUrl}" class="card-img-top" alt="Book Image">
        <div class="card-body">
          <h5 class="card-title">${book.title}</h5>
          <p class="card-text">by ${book.author}</p>
          <p class="card-text">₱${book.price}</p>
          <button class="btn btn-primary" onclick='addToCart(${JSON.stringify(book)})'>Add to Cart</button>
        </div>
      </div>`;
    list.appendChild(div);
  });
}

function addToCart(book) {
  const userId = 1;
  const quantity = 1;

  fetch(`http://localhost:8080/api/carts/add?bookId=${book.bookId}&quantity=${quantity}`, {
    method: "POST",
    credentials: "include"
  })
    .then(res => {
      if (!res.ok) {
        throw new Error("Failed to add to cart");
      }
      return res.json();
    })
    .then(data => {
      console.log("Added to cart:", data);
      alert("Book added to cart (server)!");
    })
    .catch(err => {
      console.error("Error adding to cart:", err);
      alert("Failed to add book to cart.");
    });
}
