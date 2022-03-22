# pos-spring

Working - 
https://drive.google.com/file/d/1hT8wt_p1P-KtAFUFlGW9Vm6cpaY8Gr7k/view?usp=sharing

A PoS (Point of Sale) system is what we see in grocery stores, where the sales manager scans our products and gives us a receipt.
You can add brands & products, keep track of sales and inventory and create orders and their reports.

Functionalities
Upload brand/category details using TSV, from UI
View + Create + Edit a brand detail using UI
Upload product details using TSV, from UI
View + Create + Edit a product detail using UI
Upload product wise inventory using TSV
Edit inventory for a product
Create a customer order -
Enter barcode, Quantity, MRP
On creating order, the inventory is reduced
Edit an existing customer order
Download a PDF for a customer invoice
Reports -
Inventory report
Brand
reportSales report (for a duration)
Tech Stack
Spring, Hibernate, MySQL, Thymeleaf, JQuery

Commands
Build: mvn clean install

Run: mvn jetty:run
