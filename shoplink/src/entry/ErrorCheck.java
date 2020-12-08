package entry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.dao.EmailCheckDao;

public class ErrorCheck
{
    private List<String> errors;

    public ErrorCheck()
    {
        this.errors = new ArrayList<String>();
    }

    public void requiredCheck(String value, String name)
    {
        if (value == null || value.trim().isEmpty())
        {
            this.errors.add(name + "を入力して下さい");
        }
    }

    public void regExpCheck(String value, String regExp, String name)
    {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(value);
        if (!m.matches())
        {
            this.errors.add(name + "を正しい形式で入力して下さい");
        }
    }

    public void passwordCheck(String value1, String value2)
    {
        Pattern p = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[(a-zA-Z0-9(\\.\\-/_)*]{8,15}$");
        Matcher m = p.matcher(value1);
        if (!m.matches())
        {
            this.errors.add("パスワードは半角英数字の組み合わせと記号(.-/_)がご利用頂けます");
        }
        else if (!value1.equals(value2))
        {
            this.errors.add("パスワードが一致しません");
        }
    }

    public void duplicateCheck(String value)
    {
        String result = null;

        try
        {
            result = EmailCheckDao.selectCustomerByEmail(value);
        }
        catch (SQLException e)
        {
            this.errors.add("データベースにアクセスできませんでした");
            e.printStackTrace();
        }
        if (!(result == null))
        {
            this.errors.add("そのメールアドレスは利用されています");
        }
    }

    public void logInCheck(String email, String password)
    {
        String result = null;

        try
        {
            result = EmailCheckDao.selectCustomerByEmail(email);
        }
        catch (SQLException e)
        {
            this.errors.add("データベースに接続できませんでした");
            e.printStackTrace();
        }

        if (result == null)
        {
            this.errors.add("そのメールアドレスは登録されていません");
        }
        else
        {
            if (!password.equals(result))
            {
                this.errors.add("パスワードが間違っています");
            }
        }
    }

    public boolean hasErrors()
    {
        return !this.errors.isEmpty();
    }

    public List<String> errorList()
    {
        return errors;
    }

}
