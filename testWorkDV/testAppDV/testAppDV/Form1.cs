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
                    listBox1.Items.Add(Convert.ToString(sqlReader["Id"]) + "    " +
                                        Convert.ToString(sqlReader["Name"]) + "    " +
                                        Convert.ToString(sqlReader["Date"]) + "    " +
                                        Convert.ToString(sqlReader["Addressee"]) + "     " +
                                        Convert.ToString(sqlReader["Sender"]) + "     " +
                                        Convert.ToString(sqlReader["Tags"]) + "    " +
                                        Convert.ToString(sqlReader["Content"]));
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

        private void выходToolStripMenuItem_Click(object sender, EventArgs e)
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

            if (!string.IsNullOrEmpty(textBox1.Text) && !string.IsNullOrWhiteSpace(textBox1.Text) &&
                !string.IsNullOrEmpty(textBox2.Text) && !string.IsNullOrWhiteSpace(textBox2.Text) &&
                !string.IsNullOrEmpty(textBox3.Text) && !string.IsNullOrWhiteSpace(textBox3.Text) &&
                !string.IsNullOrEmpty(textBox4.Text) && !string.IsNullOrWhiteSpace(textBox4.Text) &&
                !string.IsNullOrEmpty(textBox5.Text) && !string.IsNullOrWhiteSpace(textBox5.Text) &&
                !string.IsNullOrEmpty(textBox6.Text) && !string.IsNullOrWhiteSpace(textBox6.Text) &&
                dateReg)
            {
                SqlCommand command = new SqlCommand("INSERT INTO [testLettersDV] (Name, Date, Addressee, Sender, Tags, Content)VALUES(@Name, @Date, @Addressee, @Sender, @Tags, @Content)", sqlConnection);

                command.Parameters.AddWithValue("Name", textBox1.Text);
                command.Parameters.AddWithValue("Date", textBox2.Text);
                command.Parameters.AddWithValue("Addressee", textBox3.Text);
                command.Parameters.AddWithValue("Sender", textBox4.Text);
                command.Parameters.AddWithValue("Tags", textBox5.Text);
                command.Parameters.AddWithValue("Content", textBox6.Text);

                textBox1.Text = null;
                textBox2.Text = null;
                textBox3.Text = null;
                textBox4.Text = null;
                textBox5.Text = null;
                textBox6.Text = null;

                await command.ExecuteNonQueryAsync();
            } else
            {
                label15.Visible = true;
                label15.Text = "Заполните ВСЕ поля!";
                if (!dateReg)
                {
                    label16.Visible = true;
                    label16.Text = "Дата регистрации должна быть в формате yyyy - MM - dd!";
                }
            }          
            
        }

        private async void обновитьToolStripMenuItem_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();

            SqlDataReader sqlReader = null;

            SqlCommand command = new SqlCommand("SELECT * FROM [testLettersDV]", sqlConnection);

            try
            {
                sqlReader = await command.ExecuteReaderAsync();
                
                while (await sqlReader.ReadAsync())
                {
                    listBox1.Items.Add(Convert.ToString(sqlReader["Id"]) + "    " +
                                        Convert.ToString(sqlReader["Name"]) + "    " +
                                        Convert.ToString(sqlReader["Date"]) + "    " +
                                        Convert.ToString(sqlReader["Addressee"]) + "     " +
                                        Convert.ToString(sqlReader["Sender"]) + "     " +
                                        Convert.ToString(sqlReader["Tags"]) + "    " +
                                        Convert.ToString(sqlReader["Content"]));
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

        static bool TextIsDate(string text)
        {
            var dateFormat = "yyyy-MM-dd";
            DateTime scheduleDate;
            if (DateTime.TryParseExact(text, dateFormat, DateTimeFormatInfo.InvariantInfo, DateTimeStyles.None, out scheduleDate))
            {
                return true;
            }
            return false;
        }

        private async void button2_Click(object sender, EventArgs e)
        {
            if (label17.Visible)
                label17.Visible = false;
            if (label18.Visible)
                label18.Visible = false;
            var dateReg = TextIsDate(textBox11.Text);            

            if (!string.IsNullOrEmpty(textBox7.Text) && !string.IsNullOrWhiteSpace(textBox7.Text) &&
                !string.IsNullOrEmpty(textBox8.Text) && !string.IsNullOrWhiteSpace(textBox8.Text) &&
                !string.IsNullOrEmpty(textBox9.Text) && !string.IsNullOrWhiteSpace(textBox9.Text) &&
                !string.IsNullOrEmpty(textBox10.Text) && !string.IsNullOrWhiteSpace(textBox10.Text) &&
                !string.IsNullOrEmpty(textBox11.Text) && !string.IsNullOrWhiteSpace(textBox11.Text) &&
                !string.IsNullOrEmpty(textBox12.Text) && !string.IsNullOrWhiteSpace(textBox12.Text) &&
                dateReg)
            {
                SqlCommand command = new SqlCommand("UPDATE [testLettersDV] SET [Name]=@Name, [Date]=@Date, [Addressee]=@Addressee, [Sender]=@Sender, [Tags]=@Tags, [Content]=@Content WHERE [Id]=@Id", sqlConnection);

                command.Parameters.AddWithValue("Id", textBox13.Text);
                command.Parameters.AddWithValue("Name", textBox12.Text);
                command.Parameters.AddWithValue("Date", textBox11.Text);
                command.Parameters.AddWithValue("Addressee", textBox10.Text);
                command.Parameters.AddWithValue("Sender", textBox9.Text);
                command.Parameters.AddWithValue("Tags", textBox8.Text);
                command.Parameters.AddWithValue("Content", textBox7.Text);

                textBox12.Text = null;
                textBox11.Text = null;
                textBox10.Text = null;
                textBox9.Text = null;
                textBox8.Text = null;
                textBox7.Text = null;
                textBox13.Text = null;

                await command.ExecuteNonQueryAsync();

            } else if (string.IsNullOrEmpty(textBox13.Text) && string.IsNullOrWhiteSpace(textBox13.Text))
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
                    label18.Text = "Дата регистрации должна быть в формате yyyy - MM - dd!";
                }
            }
        }

        private async void button3_Click(object sender, EventArgs e)
        {
            if (label19.Visible)
                label19.Visible = false;

            if (!string.IsNullOrEmpty(textBox14.Text) && !string.IsNullOrWhiteSpace(textBox14.Text))
            {
                SqlCommand command = new SqlCommand("DELETE FROM [testLettersDV] WHERE [Id]=@Id", sqlConnection);

                command.Parameters.AddWithValue("Id", textBox14.Text);

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

            if (!string.IsNullOrEmpty(textBox13.Text) && !string.IsNullOrWhiteSpace(textBox13.Text))
            {
                SqlCommand command = new SqlCommand("SELECT * FROM [testLettersDV] WHERE [Id]=@Id", sqlConnection);

                command.Parameters.AddWithValue("Id", textBox13.Text);

                SqlDataReader sqlReader = null;

                sqlReader = await command.ExecuteReaderAsync();
                try
                {                    
                    while (await sqlReader.ReadAsync())
                    {
                        textBox12.Text = Convert.ToString(sqlReader["Name"]);
                        textBox11.Text = Convert.ToString(sqlReader["Date"]);
                        textBox10.Text = Convert.ToString(sqlReader["Addressee"]);
                        textBox9.Text = Convert.ToString(sqlReader["Sender"]);
                        textBox8.Text = Convert.ToString(sqlReader["Tags"]);
                        textBox7.Text = Convert.ToString(sqlReader["Content"]);
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
    }
}
