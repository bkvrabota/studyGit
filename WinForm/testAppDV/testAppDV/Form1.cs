using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace testAppDV
{
    public partial class Form1 : Form
    {
        SqlConnection sqlConnection;

        private string columnId = "Id";
        private string columnName = "Name";
        private string columnDate = "Date";
        private string columnAddressee = "Addressee";
        private string columnSender = "Sender";
        private string columnTags = "Tags";
        private string columnContent = "Content";

        public Form1()
        {
            InitializeComponent();
        }

        private async void Form1_Load(object sender, EventArgs e)
        {
            string connectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\DatabaseDVtest.mdf;Integrated Security=True;Connect Timeout=30";

            sqlConnection = new SqlConnection(connectionString);

            await sqlConnection.OpenAsync();

            SqlDataReader sqlReader = null;

            SqlCommand command = new SqlCommand("SELECT * FROM [testLettersDV]", sqlConnection);

            try
            {
                sqlReader = await command.ExecuteReaderAsync();
                this.testLettersDVTableAdapter.Fill(this.databaseDVtestDataSet.testLettersDV);
                while (await sqlReader.ReadAsync())
                {
                    listBox1.Items.Add(Convert.ToString(sqlReader[columnId]) + "    " +
                                        Convert.ToString(sqlReader[columnName]) + "    " +
                                        Convert.ToString(sqlReader[columnDate]) + "    " +
                                        Convert.ToString(sqlReader[columnAddressee]) + "     " +
                                        Convert.ToString(sqlReader[columnSender]) + "     " +
                                        Convert.ToString(sqlReader[columnTags]) + "    " +
                                        Convert.ToString(sqlReader[columnContent]));
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message.ToString(), ex.Source.ToString(), MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            finally
            {
                if (sqlReader != null)
                    sqlReader.Close();
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (sqlConnection != null && sqlConnection.State != ConnectionState.Closed)
                sqlConnection.Close();
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            if (sqlConnection != null && sqlConnection.State != ConnectionState.Closed)
                sqlConnection.Close();
        }        

        private async void button1_Click(object sender, EventArgs e)
        {
            if (label15.Visible)
                label15.Visible = false;
            if (label16.Visible)
                label16.Visible = false;
            var dateReg = TextIsDate(textBox2.Text);

            if (CheckBoxNull(textBox1.Text) && CheckBoxNull(textBox2.Text) && CheckBoxNull(textBox3.Text) && CheckBoxNull(textBox4.Text) && 
                CheckBoxNull(textBox5.Text) && CheckBoxNull(textBox6.Text) && dateReg)
            {
                SqlCommand command = new SqlCommand("INSERT INTO [testLettersDV] (Name, Date, Addressee, Sender, Tags, Content)VALUES(@Name, @Date, @Addressee, @Sender, @Tags, @Content)", sqlConnection);

                command.Parameters.AddWithValue(columnName, textBox1.Text);
                command.Parameters.AddWithValue(columnDate, textBox2.Text);
                command.Parameters.AddWithValue(columnAddressee, textBox3.Text);
                command.Parameters.AddWithValue(columnSender, textBox4.Text);
                command.Parameters.AddWithValue(columnTags, textBox5.Text);
                command.Parameters.AddWithValue(columnContent, textBox6.Text);

                textBox1.Text = "";
                textBox2.Text = "";
                textBox3.Text = "";
                textBox4.Text = "";
                textBox5.Text = "";
                textBox6.Text = "";

                await command.ExecuteNonQueryAsync();
            } else
            {
                label15.Visible = true;
                label15.Text = "Заполните ВСЕ поля!";
                if (!dateReg)
                {
                    label16.Visible = true;
                    label16.Text = "Форматы даты: yyyy-MM-dd, dd.MM.yyyy, dd/MM/yyyy!";
                }
            }          
            
        }

        private async void renewlistToolStripMenuItem_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();

            SqlDataReader sqlReader = null;

            SqlCommand command = new SqlCommand("SELECT * FROM [testLettersDV]", sqlConnection);

            try
            {
                sqlReader = await command.ExecuteReaderAsync();
                
                while (await sqlReader.ReadAsync())
                {
                    listBox1.Items.Add(Convert.ToString(sqlReader[columnId]) + "    " +
                                        Convert.ToString(sqlReader[columnName]) + "    " +
                                        Convert.ToString(sqlReader[columnDate]) + "    " +
                                        Convert.ToString(sqlReader[columnAddressee]) + "     " +
                                        Convert.ToString(sqlReader[columnSender]) + "     " +
                                        Convert.ToString(sqlReader[columnTags]) + "    " +
                                        Convert.ToString(sqlReader[columnContent]));
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message.ToString(), ex.Source.ToString(), MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            finally
            {
                if (sqlReader != null)
                    sqlReader.Close();
            }
        }        

        private async void button2_Click(object sender, EventArgs e)
        {
            if (label17.Visible)
                label17.Visible = false;
            if (label18.Visible)
                label18.Visible = false;
            var dateReg = TextIsDate(textBox11.Text);            

            if (CheckBoxNull(textBox7.Text) && CheckBoxNull(textBox8.Text) && CheckBoxNull(textBox9.Text) && CheckBoxNull(textBox10.Text) &&
                CheckBoxNull(textBox11.Text) && CheckBoxNull(textBox12.Text) && dateReg)
            {
                SqlCommand command = new SqlCommand("UPDATE [testLettersDV] SET [Name]=@Name, [Date]=@Date, [Addressee]=@Addressee, [Sender]=@Sender, [Tags]=@Tags, [Content]=@Content WHERE [Id]=@Id", sqlConnection);

                command.Parameters.AddWithValue(columnId, textBox13.Text);
                command.Parameters.AddWithValue(columnName, textBox12.Text);
                command.Parameters.AddWithValue(columnDate, textBox11.Text);
                command.Parameters.AddWithValue(columnAddressee, textBox10.Text);
                command.Parameters.AddWithValue(columnSender, textBox9.Text);
                command.Parameters.AddWithValue(columnTags, textBox8.Text);
                command.Parameters.AddWithValue(columnContent, textBox7.Text);

                textBox12.Text = "";
                textBox11.Text = "";
                textBox10.Text = "";
                textBox9.Text = "";
                textBox8.Text = "";
                textBox7.Text = "";
                textBox13.Text = "";

                await command.ExecuteNonQueryAsync();

            } else if (!CheckBoxNull(textBox13.Text) && CheckId(textBox13.Text))
            {
                label17.Visible = true;
                label17.Text = "Укажите ID письма!";
            } else
            {
                label17.Visible = true;
                label17.Text = "Заполните ВСЕ поля!";
                if (!dateReg)
                {
                    label18.Visible = true;
                    label18.Text = "Форматы даты: yyyy-MM-dd, dd.MM.yyyy, dd/MM/yyyy!";
                }
            }
        }

        private async void button3_Click(object sender, EventArgs e)
        {
            if (label19.Visible)
                label19.Visible = false;

            if (CheckBoxNull(textBox14.Text) && CheckId(textBox14.Text))
            {
                SqlCommand command = new SqlCommand("DELETE FROM [testLettersDV] WHERE [Id]=@Id", sqlConnection);

                command.Parameters.AddWithValue(columnId, textBox14.Text);

                textBox14.Text = null;

                await command.ExecuteNonQueryAsync();

            } else
            {
                label19.Visible = true;
                label19.Text = "Укажите ID письма!";
            }
        }

        private async void button4_Click(object sender, EventArgs e)
        {
            if (label17.Visible)
                label17.Visible = false;

            if (CheckBoxNull(textBox13.Text) && CheckId(textBox13.Text))
            {
                SqlCommand command = new SqlCommand("SELECT * FROM [testLettersDV] WHERE [Id]=@Id", sqlConnection);

                command.Parameters.AddWithValue(columnId, textBox13.Text);

                SqlDataReader sqlReader = null;

                sqlReader = await command.ExecuteReaderAsync();
                try
                {                    
                    while (await sqlReader.ReadAsync())
                    {
                        textBox12.Text = Convert.ToString(sqlReader[columnName]);
                        textBox11.Text = Convert.ToString(sqlReader[columnDate]);
                        textBox10.Text = Convert.ToString(sqlReader[columnAddressee]);
                        textBox9.Text = Convert.ToString(sqlReader[columnSender]);
                        textBox8.Text = Convert.ToString(sqlReader[columnTags]);
                        textBox7.Text = Convert.ToString(sqlReader[columnContent]);
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message.ToString(), ex.Source.ToString(), MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                finally
                {
                    if (sqlReader != null)
                        sqlReader.Close();
                }
                
                await command.ExecuteNonQueryAsync();

            } else
            {
                label17.Visible = true;
                label17.Text = "Укажите ID письма!";
            }
        }

        private bool CheckBoxNull(string textBox)
        {
            if (!string.IsNullOrEmpty(textBox) && !string.IsNullOrWhiteSpace(textBox))
            {
                return true;
            }
            return false;
        }

        private bool CheckId(string id)
        {
            int i;
            if (int.TryParse(id, out i))
            {
                return true;
            }
            return false;
        }

        static bool TextIsDate(string text)
        {
            var dateFormat1 = "yyyy-MM-dd";
            var dateFormat2 = "dd.MM.yyyy";
            var dateFormat3 = "dd/MM/yyyy";
            DateTime scheduleDate;
            if (DateTime.TryParseExact(text, dateFormat1, DateTimeFormatInfo.InvariantInfo, DateTimeStyles.None, out scheduleDate) ||
                DateTime.TryParseExact(text, dateFormat2, DateTimeFormatInfo.InvariantInfo, DateTimeStyles.None, out scheduleDate) ||
                DateTime.TryParseExact(text, dateFormat3, DateTimeFormatInfo.InvariantInfo, DateTimeStyles.None, out scheduleDate))
            {
                return true;
            }
            return false;
        }
    }
}
