/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Categories;
import model.Item;
import model.Product;
import model.Question;
import model.Review;
import model.Users;

/**
 *
 * @author FPTSHOP
 */
public class DAO extends DBContext {

    public Users check(String username, String password) {
        String sql = "SELECT [Id]\n"
                + "      ,[Name]\n"
                + "      ,[Address]\n"
                + "      ,[Phone]\n"
                + "      ,[Username]\n"
                + "      ,[Password]\n"
                + "      ,[role]\n"
                + "  FROM [dbo].[Users]"
                + "  WHERE Username = ? and Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Users a = new Users(rs.getInt(1)
                                    ,rs.getString(2)
                                    , rs.getString(3) 
                                    ,rs.getString(4)
                                    ,rs.getString(5)
                                    ,rs.getString(6)
                                    ,rs.getString(7));
                return a;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public void insertUsers(Users c) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Name]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]"
                + "           ,[Username]"
                + "           ,[Password]"
                + "           ,[Role])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setString(2, c.getAddress());
            st.setString(3, c.getPhone());
            st.setString(4, c.getUsername());
            st.setString(5, c.getPass());
            st.setString(6, "customer");
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insertUsers: " + e.getMessage());
        }
    }

    public Users CheckUsersExist(String username) {

        String sql = "Select * from Users where Username= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Users c = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                return c;
            }
        } catch (SQLException e) {
            System.out.println("CheckUsersExist: " + e.getMessage());
        }
        return null;
    }

    public List<Categories> getAllCategories() {
        List<Categories> list = new ArrayList<>();
        String sql = "Select * from Product_Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt("Id"), rs.getString("Category_Name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("getAllCategories: " + e.getMessage());
        }
        return list;
    }

    public Categories getCategoryById(int id) {

        String sql = "Select * from Product_Category where ID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Categories c = new Categories(rs.getInt("Id"), rs.getString("Category_Name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "select p.id,p.name,p.image,p.price, p.quantity,p.Description,p.Product_Category_ID cid, "
                    + " c.Category_Name cname from product p "
                    + " inner join Product_Category c on p.Product_Category_ID = c.id";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
//                Categories m = new Categories(rs.getInt("cid"), rs.getString("cname"));
                p.setCategory(rs.getString("cname"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("getAllProduct: " + e.getMessage());
        }
        return list;
    }

    public Product getproductById(int id) {

        try {
            String sql = "select p.id,p.name,p.image,p.price, p.quantity,p.Description,p.Product_Category_ID cid,p.Saleoff, "
                    + " c.Category_Name cname from product p "
                    + " inner join Product_Category c on p.Product_Category_ID = c.id"
                    + " where p.id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("Description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSale(rs.getInt("Saleoff"));
//                Categories m = new Categories(rs.getInt("cid"), rs.getString("cname"));
                p.setCategory(String.valueOf(rs.getInt("cid")));
                return p;
            }
        } catch (SQLException e) {
            System.out.println("getAllProduct: " + e.getMessage());
        }
        return null;
    }

    public List<Product> checkProduct(int[] id) {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "select p.id,p.name,p.image,p.price, p.quantity,p.Description,p.Product_Category_ID cid, "
                    + " c.Category_Name cname from product p "
                    + " inner join Product_Category c on p.Product_Category_ID = c.id"
                    + " where 1=1";
            if (id != null) {
                sql += " and p.cid in(";
                for (int i = 0; i < id.length; i++) {
                    sql += id[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
//                    Categories m = new Categories(rs.getInt("cid"), rs.getString("cname"));
                p.setCategory(rs.getString("cname"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("getAllProduct: " + e.getMessage());
        }
        return list;
    }

    public List<Product> SearchProduct(String key) {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "select p.id,p.name,p.image,p.price, p.quantity,p.Description,p.Product_Category_ID cid,p.Saleoff, "
                    + " c.Category_Name cname from product p "
                    + " inner join Product_Category c on p.Product_Category_ID = c.id where p.name like ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSale(rs.getInt("Saleoff"));
                //Categories m = new Categories(rs.getInt("cid"), rs.getString("cname"));
                p.setCategory(rs.getString("cname"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("getAllProduct: " + e.getMessage());
        }
        return list;
    }

    public List<Product> getProductByCid(int cid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ID\n"
                + "      , p.name\n"
                + "      , p.quantity\n"
                + "      , p.price\n"
                + "      , p.Description\n"
                + "      , p.image\n"
                + "      , p.Product_Category_ID\n"
                + "      , p.Saleoff\n   "
                + "      , c.Category_Name cname\n"
                + "  FROM [dbo].[product] p inner join Product_Category c on p.Product_Category_ID = c.id"
                + "  Where 1=1";
        if (cid != 0) {
            sql += " and p.Product_Category_ID = " + cid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            //st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("Description"));
                p.setImage(rs.getString("image"));
                p.setSale(rs.getInt("Saleoff"));
//                Categories c = getCategoryById(rs.getInt("Product_Category_ID"));
                p.setCategory(rs.getString("cname"));
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Product> getListByPage(List<Product> list,
            int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Product> getProductByPrice(double from, double to) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.[ID]\n"
                + "      ,p.[name]\n"
                + "      ,p.[quantity]\n"
                + "      ,p.[price]\n"
                + "      ,p.[Description]\n"
                + "      ,p.[image]\n"
                + "      ,p.[Product_Category_ID]\n"
                + "      , p.Saleoff\n   "
                + "      , c.Category_Name cname\n"
                + "  FROM [dbo].[product] p inner join Product_Category c on p.Product_Category_ID = c.id\n"
                + "  Where p.price between ? and ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, from);
            st.setDouble(2, to);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("Description"));
                p.setImage(rs.getString("image"));
                p.setSale(rs.getInt("Saleoff"));
//                Categories c = getCategoryById(rs.getInt("Product_Category_ID"));
                p.setCategory(rs.getString("cname"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("getProductByPrice: " + e.getMessage());
        }
        return list;
    }

    public void updateProduct(Product p) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [Description] = ?\n"
                + "      ,[Image] = ?\n"
                + "      ,[Name] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[Product_Category_ID] = ?\n"
                + "      ,[Saleoff] = ?  "
                + " WHERE ID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getDescription());
            st.setString(2, p.getImage());
            st.setString(3, p.getName());
            st.setDouble(4, p.getPrice());
            st.setInt(5, p.getQuantity());
            st.setInt(6, Integer.parseInt(p.getCategory()));
            st.setInt(7, p.getSale());
            st.setInt(8, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Review> getAllReviewForOneProduct(int id) {
        List<Review> data = new ArrayList<>();
        String sql = "SELECT r.[Id]\n"
                + "      ,r.[Review]\n"
                + "      ,r.[Date_post]\n"
                + "      ,r.[UserId]\n"
                + "      ,r.[ProductId]\n"
                + "      ,u.Name, p.name   "
                + "  FROM [dbo].[Review] r join Users u on r.[UserId]=u.id"
                + "  join Product p on r.[ProductId]= p.id"
                + "     where p.id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt(1));
                r.setReview(rs.getString(2));
                r.setDate_post(String.valueOf(rs.getDate(3)));
                r.setUid(rs.getString(6));
                r.setPid(rs.getString(7));
                data.add(r);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public int getIdByUserName(String username) {
        String sql = "SELECT id from Users where Username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getIdByUserName" + e.getMessage());
        }
        return 0;
    }

    public void addReview(Review r) {
        String sql = "INSERT INTO [dbo].[Review]\n"
                + "           ([Review]\n"
                + "           ,[Date_post]\n"
                + "           ,[UserId]\n"
                + "           ,[ProductId]) "
                + "            Values(?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, r.getReview());
            st.setString(2, r.getDate_post());
            st.setInt(3, Integer.parseInt(r.getUid()));
            st.setInt(4, Integer.parseInt(r.getPid()));
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("addReview" + e.getMessage());
        }
    }

    public List<Product> searchByCheck(int[] cid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ID\n"
                + "      , p.name\n"
                + "      , p.quantity\n"
                + "      , p.price\n"
                + "      , p.Description\n"
                + "      , p.image\n"
                + "      , p.Product_Category_ID\n"
                + "      , c.Category_Name cname\n"
                + "  FROM [dbo].[product] p inner join Product_Category c on p.Product_Category_ID = c.id"
                + "  Where 1=1";
        if (cid != null && cid[0] != 0) {
            sql += " and p.Product_Category_ID in (";
            for (int i = 0; i < cid.length; i++) {
                sql += cid[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("Description"));
                p.setImage(rs.getString("image"));

                p.setCategory(rs.getString("cname"));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchByCheck" + e.getMessage());
        }
        return list;
    }

    public void addOrder(Users u, Cart cart) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into Orders values(?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setDouble(2, cart.getTotalMoney());
            st.setInt(3, u.getiD());
            System.out.println(u.getiD());
            st.executeUpdate();
            String sql1 = "select top 1 id from Orders order by id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("id");
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into OrderLine values (?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setDouble(1, i.getPrice());
                    st2.setInt(2, i.getQuantity());
                    st2.setInt(3, i.getProduct().getId());
                    st2.setInt(4, oid);
                    st2.executeUpdate();
                }
            }
            String sql3 = "update Product set Quantity = Quantity-? where id = ?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getId());
                st3.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("addOrder : " + e.getMessage());
        }
    }

    public List<Product> SortProductByPrice(String sort) {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "select p.id,p.name,p.image,p.price, p.quantity,p.Description,p.Product_Category_ID cid, "
                    + " c.Category_Name cname from product p "
                    + " inner join Product_Category c on p.Product_Category_ID = c.id"
                    + " order by p.price ";
            if(sort.equals("0")) {
                sql += " desc ";
            }
            if(sort.equals("1")) {
                sql += " asc ";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
//                Categories m = new Categories(rs.getInt("cid"), rs.getString("cname"));
                p.setCategory(rs.getString("cname"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("SortProductByPrice: " + e.getMessage());
        }
        return list;
    }

    public List<Question> getQuestion() {
        List<Question> data = new ArrayList<>();
       String sql = "select * from Question" ;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Question q = new Question();
                q.setContent(rs.getString(1));
                q.setA(rs.getString(2));
                q.setB(rs.getString(3));
                q.setC(rs.getString(4));
                q.setD(rs.getString(5));
                q.setAnswer(rs.getString(6));
                data.add(q);
            }
        } catch (Exception e) {
            System.out.println("getFirstQuestion" + e.getMessage());
        }
        return data;
    }

    public Question getAnswerByQuestion(String a) {
        String sql = "Select * from Question where content = '" + a + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Question q = new Question();
                q.setContent(rs.getString(1));
                q.setA(rs.getString(2));
                q.setB(rs.getString(3));
                q.setC(rs.getString(4));
                q.setD(rs.getString(5));
                q.setAnswer(rs.getString(6));
                return q;
            }
        } catch (Exception e) {
            System.out.println("getFirstQuestion" + e.getMessage());
        }
        return null;
    }
    public ArrayList<Question> getListQuestion() {
        ArrayList<Question> data = new ArrayList<Question>();
        try {
            String strSQL = "select * from Question";
            PreparedStatement st = connection.prepareStatement(strSQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String content = rs.getString(1);
                String a = rs.getString(2);
                String b= rs.getString(3);
                 String c= rs.getString(4);
                  String d= rs.getString(5);
                   String answer= rs.getString(6);
                Question q = new Question(content, a, b, c, d, answer);
                data.add(q);
            }
        } catch (Exception e) {
            System.out.println("getListQuestion:" + e.getMessage());
        }
        return data;
    }

    public int resetPassword(String nPass,String email) {
        int rowCount=0;
        try {
            String strSQL = "update users set Password = ? where email=?";
            PreparedStatement st = connection.prepareStatement(strSQL);
            st.setString(1, nPass);
            st.setString(2, email);
            rowCount = st.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("getListQuestion:" + e.getMessage());
        }
        return rowCount;
    }
}
