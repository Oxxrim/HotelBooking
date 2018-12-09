# HotelBooking
To run the application you need to clone my project to your ide, and create database in h2 with username: oxxrim,<br/>
password: DiMoN1998 or change this parameters on application properties.<br/><br>
User can book the room and view his bookings only after create user, 
also you can not do something for past users because in application are not included authentication and authorization.<br><br>
1)View list of available rooms (POST request "localhost:2020/rooms/available" <br/>
Request body: 
"{
	"since": "09.12.2018", 
	"to": "18.12.2018"
}") For example.<br/><br/>
2)View rooms filterd by category.(GET request "localhost:2020/rooms/{category}" For example "localhost:2020/rooms/lux")<br/><br/>
3)Create user (POST request "localhost:2020/user" <br/>
Request body:
{
	"username": "dima", 
	"phoneNumber": "0954401531"
})<br><br>
4)User can book the room.(POST request "localhost:2020/user/book/{roomNumber}" for example "localhost:2020/user/book/1" <br>
Requst body:
{
	"breakfast": true,
	"cleaning": true,
	"since": "09.12.2018", 
	"to": "20.12.2018"
})<br><br>
5)User can view his bookings.(GET request "localhost:2020/user/bookings")<br><br>
6)User can get the total price of the booking.(GET request "localhost:2020/user/bookings/{bookId}" for example "localhost:2020/user/bookings/258")<br><br>
7)View all bookings for the hotel.(GET request "localhost:2020/hotel/bookings")<br><br>
8)User can delete his book(DELETE request "localhost:2020/user/book/{bookId}" for example "localhost:2020/user/book/290")<br><br>
9)User can update his book(PUT request "localhost:2020/user/book/{bookId}/{roomNumber}" for example "localhost:2020/user/book/290/1" <br>
Request body:
{
	"breakfast": true,
	"cleaning": true,
	"since": "27.12.2018", 
	"to": "29.12.2018"
})<br><br>
