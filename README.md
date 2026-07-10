# 📱 Android MySQL Data Fetch Application

এটি একটি সিম্পল এবং এস্থেটিক অ্যান্ড্রয়েড অ্যাপ্লিকেশন, যা **Volley Library** ব্যবহার করে লোকাল সার্ভার (MySQL Database) থেকে JSON ডাটা রিয়েল-টাইমে ফেচ (Fetch) করে একটি কাস্টম `ListView`-তে প্রদর্শন করে। অ্যাপটিতে ডাটা ক্লিকে ডিটেইলস স্ক্রিনে প্রিমিয়াম সিনেমাটিক লুকে ডাটা দেখানোর ফিচার রয়েছে।

---

## ✨ ফিচারসমূহ
* **রিয়েল-টাইম ডাটা ফেচিং:** Volley এবং JSON-এর মাধ্যমে ব্যাকএন্ড থেকে ডাটা লোড।
* **কাস্টম অ্যাডাপ্টার:** `BaseAdapter` ব্যবহার করে ইমেজের কোণা নিখুঁতভাবে রাউন্ডেড করা কাস্টম লিস্টভিউ।
* **গ্লাইড ইমেজ লোডার:** `Glide` লাইব্রেরির মাধ্যমে মসৃণভাবে ইউআরএল থেকে ইমেজ লোড।
* **মডার্ন এস্থেটিক ইউআই (UI):** ডিটেইলস স্ক্রিনে ফুল-স্ক্রিন ইমেজ এবং কাস্টম ব্ল্যাক গ্রাডিয়েন্ট ও ব্লেন্ডেড ওভারলে ডিজাইন।

---

## 🛠️ প্রযুক্তি ও লাইব্রেরি (Tech Stack)
* **Frontend:** Android Studio (Java)
* **Backend:** PHP (API)
* **Database:** MySQL
* **Libraries:** * [Volley](https://github.com/google/volley) (Networking)
  * [Glide](https://github.com/bumptech/glide) (Image Loading)
  * Material Design Components

---

## 📂 ব্যাকএন্ড কনফিগারেশন (Backend & PHP Setup)

ডাটাবেজে ডাটাগুলো ম্যানুয়ালি ইনসার্ট করা হয়েছে। নিচে সার্ভারের PHP কোড দেওয়া হলো যা ডাটাবেজ থেকে ডাটা নিয়ে JSON ফরম্যাটে আউটপুট দেয়:

### `fetch.php`
```php
// ডাটাবেজ কানেকশন
<?php
$host = "localhost:3307";
$user = "root";
$pass = "";
$dbname = "pakistani_actress";

$conn = new mysqli($host, $user, $pass, $dbname);

if ($conn->connect_error) {
    die(json_encode(array("error" => "Connection failed: " . $conn->connect_error)));
}
?>

// ডাটা ফেচ করার কুয়েরি
<?php
header("Content-Type: application/json");
include 'connection.php';

$result = $conn->query("SELECT * FROM actress_information_table");
$data = array();

while ($row = $result->fetch_assoc()) {
    $data[] = array(
        "imagePHP" => $row['image'],
        "namePHP"  => $row['name']
    );
}

echo json_encode($data, JSON_PRETTY_PRINT);
$conn->close();
?>
