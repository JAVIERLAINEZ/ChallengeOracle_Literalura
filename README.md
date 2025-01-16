"# ChallengeOracle_Literalura" 
---

# Book Catalog Application

![Book Catalog Application](header-image.jpg)

![GitHub repo size](https://img.shields.io/github/repo-size/JAVIERLAINEZ/ChallengeOracle_Literalura)
![GitHub stars](https://img.shields.io/github/stars/JAVIERLAINEZ/ChallengeOracle_Literalura?style=social)
![GitHub forks](https://img.shields.io/github/forks/JAVIERLAINEZ/ChallengeOracle_Literalura?style=social)

## Table of Contents
- [Project Description](#project-description)
- [Project Status](#project-status)
- [Features and Demonstration](#features-and-demonstration)
- [Access to the Project](#access-to-the-project)
- [Technologies Used](#technologies-used)
- [Contributors](#contributors)
- [Developers](#developers)
- [License](#license)

## Project Description
The Book Catalog Application is designed to manage and catalog books, allowing users to search, list, and register books in a PostgreSQL database. The application integrates with the Gutendex API to fetch book details and uses Java and Spring Boot for backend services.

## Project Status
The project is currently in a functional state with the following options:
1. Search for a book by title.
2. List all registered books.
3. List all registered authors and their books.
4. List authors who were alive in a specific year.
5. List books available by language.

## Features and Demonstration
### Search and Register Book
Users can search for a book by its title, and the application fetches details from the Gutendex API and registers the book in the database if it doesn't already exist. The application handle Exception errors, protecting itself from wrong inputs formats or values done by the user.

### List All Registered Books
Displays all books registered in the database with their details such as title, author, language, and number of downloads.

### List Authors and Their Books
Displays all authors with their birth and death years, and the list of books they have published.

### Authors Alive in a Specific Year
Users can enter a year to see a list of authors who were alive in that year along with their books.The application handle Exception errors, protecting itself from wrong inputs formats or values done by the user.

### List Books by Language
Allows users to select a language and displays all books available in that language.The application handle Exception errors, protecting itself from wrong inputs formats or values done by the user.

## Access to the Project
To access the project, clone the repository:
```sh
git clone https://github.com/JAVIERLAINEZ/ChallengeOracle_Literalura.git
```
Navigate to the project directory:
```sh
cd repo-name
```
Build and run the project using Maven:
```sh
mvn spring-boot:run
```

## Technologies Used
- **Java**: Programming language for developing the application.
- **Spring Boot**: Framework for building the backend services.
- **PostgreSQL**: Database for storing book and author information.
- **Gutendex API**: External API to fetch book details.

## Contributors
- **Javier**: Developer and maintainer of the project.

## Developers
- **Javier**: Lead Developer
- Additional contributors ALURA LATAM.

## License
This project is free licensed.

---

😊
