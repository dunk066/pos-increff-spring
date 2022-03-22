# pos-spring

Working - 
https://drive.google.com/file/d/1hT8wt_p1P-KtAFUFlGW9Vm6cpaY8Gr7k/view?usp=sharing

A PoS (Point of Sale) system is what we see in grocery stores, where the sales manager scans our products and gives us a receipt.
You can add brands & products, keep track of sales and inventory and create orders and their reports.

# Functionalities
1.Upload brand/category details using TSV, from UI
2.View + Create + Edit a brand detail using UI
3.Upload product details using TSV, from UI
4.View + Create + Edit a product detail using UI
5.Upload product wise inventory using TSV
6.Edit inventory for a product
7.Create a customer order -
     Enter barcode, Quantity, MRP
     On creating order, the inventory is reduced
8.Edit an existing customer order
9.Download a PDF for a customer invoice
10.Reports -
     Inventory report
     Brand report
     Sales report (for a period)
# Tech Stack
Spring, Hibernate, MySQL, Thymeleaf, JQuery

# Commands
Build: mvn clean install

Run: mvn jetty:run
