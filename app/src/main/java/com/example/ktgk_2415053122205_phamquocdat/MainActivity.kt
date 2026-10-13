package com.example.ktgk_2415053122205_phamquocdat


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ktgk_2415053122205_phamquocdat.R
class MainActivity : AppCompatActivity() {

    private lateinit var edtTenKhoanChi_205: EditText
    private lateinit var edtSoTien_205: EditText
    private lateinit var edtGhiChu_205: EditText
    private lateinit var chkDaThanhToan_205: CheckBox
    private lateinit var btnThemKhoanChi_205: Button
    private lateinit var btnXemChiTiet_205: Button

    private var khoanChiHienTai_205: KhoanChi_205? = null

    private val boPhucVụKetQua_205 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data_205 = result.data
            khoanChiHienTai_205 = data_205?.getSerializableExtra("khoan_chi_moi") as? KhoanChi_205
            Toast.makeText(this, "Cập nhật dữ liệu thành công!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thiết lập tiêu đề Toolbar theo yêu cầu
        supportActionBar?.title = "Phạm Quốc Đạt - 2415053122205 - Quản lý chi tiêu cá nhân"

        anhXaGiaoDien_205()
        xuLySuKienClick_205()
    }

    private fun anhXaGiaoDien_205() {
        edtTenKhoanChi_205 = findViewById(R.id.edtTenKhoanChi_205)
        edtSoTien_205 = findViewById(R.id.edtSoTien_205)
        edtGhiChu_205 = findViewById(R.id.edtGhiChu_205)
        chkDaThanhToan_205 = findViewById(R.id.chkDaThanhToan_205)
        btnThemKhoanChi_205 = findViewById(R.id.btnThemKhoanChi_205)
        btnXemChiTiet_205 = findViewById(R.id.btnXemChiTiet_205)
    }

    private fun xuLySuKienClick_205() {
        btnThemKhoanChi_205.setOnClickListener {
            val ten_205 = edtTenKhoanChi_205.text.toString().trim()
            val soTienStr_205 = edtSoTien_205.text.toString().trim()
            val ghiChu_205 = edtGhiChu_205.text.toString().trim()
            val trangThai_205 = chkDaThanhToan_205.isChecked

            if (ten_205.isEmpty()) {
                Toast.makeText(this, "Tên khoản chi không được để trống!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (soTienStr_205.isEmpty()) {
                Toast.makeText(this, "Số tiền không được để trống!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val soTien_205 = soTienStr_205.toDoubleOrNull()
            if (soTien_205 == null || soTien_205 <= 0) {
                Toast.makeText(this, "Số tiền phải là số lớn hơn 0!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            khoanChiHienTai_205 = KhoanChi_205("KC01", ten_205, soTien_205, ghiChu_205, trangThai_205)
            Toast.makeText(this, "Thêm khoản chi thành công!", Toast.LENGTH_SHORT).show()
        }

        btnXemChiTiet_205.setOnClickListener {
            if (khoanChiHienTai_205 == null) {
                Toast.makeText(this, "Chưa có khoản chi nào được tạo!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent_205 = Intent(this, ChiTietChiTieuActivity::class.java)
            intent_205.putExtra("obj_khoan_chi", khoanChiHienTai_205)
            boPhucVụKetQua_205.launch(intent_205)
        }
    }
}