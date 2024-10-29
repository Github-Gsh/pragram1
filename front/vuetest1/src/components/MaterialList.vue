<template>
  <div>
    <h1>Materials</h1>

    <!-- 输入框和按钮，用于根据 upSite 搜索 -->
    <div>
      <input v-model="upSite" placeholder="Enter upSite" />
      <button @click="searchMaterials">Search by upSite</button>
    </div>

    <!-- 输入框用于筛选每列 -->
    <div>
      <h3>Filter by Column</h3>
      <input v-model="filterMaterialName" placeholder="Filter by Material Name" />
      <input v-model="filterUpSite" placeholder="Filter by Up Site" />
      <input v-model="filterDownSite" placeholder="Filter by Down Site" />

      <!-- 日期筛选选择框 -->
      <label for="createDate">Filter by Create Date:</label>
      <input type="date" v-model="filterCreateDate" id="createDate" />

      <label for="updateDate">Filter by Update Date:</label>
      <input type="date" v-model="filterUpdateDate" id="updateDate" />
    </div>

    <!-- 显示材料列表 -->
    <table v-if="filteredMaterials.length > 0">
      <thead>
        <tr>
          <th>Material Name</th>
          <th>Up Site</th>
          <th>Down Site</th>
          <th>Create Date</th>
          <th>Update Date</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="material in filteredMaterials" :key="material.id">
          <td>{{ material.material_name }}</td>
          <td>{{ material.upSite }}</td>
          <td>{{ material.down_site }}</td>
          <!-- 保持显示原始的精确到秒的日期 -->
          <td>{{ material.create_date }}</td>
          <td>{{ material.up_date }}</td>
        </tr>
      </tbody>
    </table>

    <!-- 没有找到结果时的提示 -->
    <p v-else>No materials found for the given filter.</p>

    <!-- 统计按钮 -->
    <div>
      <button @click="calculateStatistics">Statistics</button>
      <div v-if="statistics">
        <h3>Statistics</h3>
        <ul>
          <li v-for="(value, key) in statistics" :key="key">
            {{ key }}: {{ formatStatisticKey(key) }} - {{ value }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { getMaterials } from '@/services/materialService';

export default {
  data() {
    return {
      materials: [], // 用于存储材料数据
      upSite: '', // 用于存储用户输入的 upSite
      filterMaterialName: '', // 用于筛选 material_name 列
      filterUpSite: '', // 用于筛选 upSite 列
      filterDownSite: '', // 用于筛选 down_site 列
      filterCreateDate: '', // 用于筛选 create_date 列
      filterUpdateDate: '', // 用于筛选 up_date 列
      statistics: null // 用于存储统计结果
    };
  },
  async created() {
    try {
      // 页面加载时获取所有材料数据
      this.materials = await getMaterials(); 
      console.log("Loaded materials:", this.materials); // Debug信息
    } catch (error) {
      console.error('Error loading materials:', error);
    }
  },
  methods: {
    // 根据用户输入的 upSite 进行搜索
    async searchMaterials() {
      try {
        // 假设 getMaterials 会获取所有材料
        this.materials = await getMaterials(); 
      } catch (error) {
        console.error('Error searching materials:', error);
      }
    },
    // 格式化统计的日期字符串为年月日
    formatStatisticKey(key) {
      if (key === 'create_date' || key === 'up_date') {
        return key === 'create_date' ? 'Create Date' : 'Update Date';
      }
      return key; // 返回其他字段名
    },
    // 计算统计信息，仅对当前筛选结果进行统计
    calculateStatistics() {
      const stats = {};
      this.filteredMaterials.forEach(material => { // 使用 filteredMaterials 进行统计
        Object.keys(material).forEach(key => {
          // 排除 id 字段
          if (key === 'id') return;
          const value = material[key];

          // 处理日期字段
          if (key === 'create_date' || key === 'up_date') {
            const dateKey = new Date(material[key]).toLocaleDateString(); // 只提取年月日
            if (!stats[key]) stats[key] = {};
            stats[key][dateKey] = (stats[key][dateKey] || 0) + 1;
          } else {
            if (!stats[key]) stats[key] = {};
            stats[key][value] = (stats[key][value] || 0) + 1;
          }
        });
      });
      this.statistics = stats;
    }
  },
  computed: {
    // 根据用户输入的筛选文本，过滤材料数据
    filteredMaterials() {
      return this.materials.filter(material => {
        const matchMaterialName = material.material_name && 
                                   material.material_name.toLowerCase().includes(this.filterMaterialName.toLowerCase());
        const matchUpSite = material.upSite && 
                            material.upSite.toLowerCase().includes(this.filterUpSite.toLowerCase());
        const matchDownSite = material.down_site && 
                              material.down_site.toLowerCase().includes(this.filterDownSite.toLowerCase());

        const matchCreateDate =
          !this.filterCreateDate ||
          new Date(material.create_date).toISOString().startsWith(this.filterCreateDate);

        const matchUpdateDate =
          !this.filterUpdateDate ||
          new Date(material.up_date).toISOString().startsWith(this.filterUpdateDate);

        return (
          matchMaterialName &&
          matchUpSite &&
          matchDownSite &&
          matchCreateDate &&
          matchUpdateDate
        );
      });
    }
  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  padding: 8px;
  border: 1px solid #ddd;
}

th {
  background-color: #f4f4f4;
}

p {
  color: red;
  font-weight: bold;
}

input, select {
  margin: 10px 0;
  padding: 5px;
  width: 100%;
}
</style>
