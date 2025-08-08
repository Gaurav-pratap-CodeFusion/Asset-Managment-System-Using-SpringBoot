# Asset Management System

This is a simple **Asset Management System** built for college or office use. It helps to keep track of different assets like laptops, chairs, projectors, etc. using barcodes or QR codes.

##  Features

- Add new assets with details like name, description, and category.
- Generate barcode/QR code for each asset.
- Assign assets to employees or students.
- View all assets in a list.
- Voice search support for assets (using Web Speech API).
- Charts and dashboard using Chart.js to show total assets and categories.
- Login system with Admin and User roles.
- Forget Password and Reset Password pages.

## 🔧 Technologies Used

| Frontend       | Backend        | Database | Tools & Libraries     |
|----------------|----------------|----------|------------------------|
| HTML, CSS      | Spring Boot    | MYSQL    | ZXing, Chart.js        |
| JavaScript     | REST APIs      | LocalDB  | Web Speech API         |
| Thymeleaf      | Spring MVC     |          |                        |



##  Setup Instructions

1. Clone the project or download the zip.
2. Open it in Visual Studio (for ASP.NET) or IntelliJ/Eclipse (for Java version).
3. Set up your SQL Server and create the required tables.
4. Update connection string in your code.
5. Run the project and start managing assets.

##  Example Chart

The dashboard shows:
- Total number of assets
- Asset categories in bar graph
- Assigned vs Available assets

##  Roles

- **Admin**: Can add/edit/delete assets and assign them.
- **User**: Can only view and search assets.

##  Voice Search

Say the asset name and it will search automatically.

##  AI Assistant (Optional)

You can add an AI chatbot (like ChatGPT) for help and search.



##  Clean UI

- Simple and mobile-friendly interface.
- No Bootstrap used. Only pure CSS.


---

## 🛠️ Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Gaurav-pratap-CodeFusion/Asset-Managment-System-Using-SpringBoot.git
   cd asset-management
