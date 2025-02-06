
## **🎬 Cinema IDLD - Plateforme de Réservation de Tickets de Cinéma**  
🚀 La Version Finale du Projet basé sur **Spring Boot**, **React**, et une architecture **Microservices** pour la réservation en ligne de billets de cinéma.  
📌 **Projet académique encadré par [MR. BOUABID OUAHIDI]**  

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## **📌 Description du Projet**  
**Cinema IDLD** est une **plateforme en ligne** permettant aux utilisateurs de **réserver des tickets de cinéma** en toute simplicité.  
L'application propose :  
- 🎥 **Consultation des films** en salle et à venir.  
- 📍 **Choix du cinéma** et de la ville.  
- 🎟️ **Sélection des places** en fonction du type de billet (étudiant, adulte).  
- 🔐 **Authentification et autorisation** via JWT.  
- 🏢 **Gestion des films, acteurs, réalisateurs et salles** par les administrateurs.  

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## **🛠️ Technologies Utilisées**  

### **📌 Backend (Microservices - Spring Boot)**  
- **Spring Boot** - Développement des microservices.  
- **Spring Cloud** - Gestion de l'architecture microservices.  
- **Spring Data JPA** - Gestion de la base de données MySQL.  
- **Spring Security & JWT** - Sécurisation et authentification des utilisateurs.  
- **Spring Cloud Gateway** - API Gateway pour centraliser les requêtes.  
- **Eureka Discovery Server** - Service d'enregistrement et découverte des microservices.  
- **WebClient** - Communication entre microservices.  
- **Docker** - Conteneurisation des services.  

### **📌 Frontend (React)**  
- **React.js** - Interface utilisateur dynamique.  
- **Redux** - Gestion d'état.  
- **Bootstrap / CSS** - Design de l'UI.  

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## **📌 Architecture du Projet**  
L'application **Cinema IDLD** suit une **architecture microservices** permettant de **modulariser** et **scalabiliser** les différentes fonctionnalités de réservation de billets de cinéma.  

L’architecture est composée de plusieurs microservices indépendants qui communiquent via **Eureka Discovery Service**, **Spring Cloud Gateway**, et **WebClient**.  
Chaque microservice est conçu pour être **autonome** et **spécifique à une fonctionnalité**, garantissant ainsi une **meilleure maintenance** et **extensibilité**.  

### **🎭 Architecture Microservices**  
- **Config-Service** : Centralise et gère la configuration de tous les microservices.  
- **Discovery-Service (Eureka Server)** : Gère l'enregistrement et la découverte des microservices.  
- **Gateway-Service** : Point d’entrée unique pour sécuriser et router les requêtes des clients.  
- **User-Service** : Gère l'inscription, l'authentification (JWT) et les rôles (admin/client).  
- **Movie-Service** : Gère les films, les catégories, les réalisateurs, les acteurs et les salles.
   
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## **📌 Perspectives d’Amélioration**  
- 💳 **Implémentation du Service de Paiement**.  
- 📧 **Ajout d’un Service de Notification (email/sms) en utilisant kafka pour les communications asynchrones**.  
- 📜 **Génération de factures après achat**.  
- 🔗 **Amélioration de l’intégration Frontend/Backend**.  
- 🔗 **Intégration des LLMs pour créer un system de recommendations des films selon chaque client**.  

🚀 **Contributions et suggestions sont les bienvenues !** 😊  

