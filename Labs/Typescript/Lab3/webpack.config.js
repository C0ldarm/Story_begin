const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin'); // Import HtmlWebpackPlugin

module.exports = {
  mode: 'development',
  entry: './src/index.ts', // Entry point for your application
  output: {
    filename: 'index.js', // Output filename for the bundled JavaScript
    path: path.resolve(__dirname, 'dist'), // Output directory for the bundle
    clean: true, // Clean the output directory before each build
  },
  resolve: {
    extensions: ['.ts', '.js'], // Add support for TypeScript and JavaScript files
  },
  module: {
    rules: [
      {
        test: /\.ts$/, // Rule for TypeScript files
        use: 'ts-loader',
        exclude: /node_modules/,
      },
      {
        test: /\.css$/, // Rule for CSS files
        use: ['style-loader', 'css-loader'],
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html', // Path to your HTML template
      filename: 'index.html', // Output filename in dist
    }),
  ],
  devServer: {
    static: [
        {
          directory: path.join(__dirname, 'dist'),
        },
        {
          directory: path.join(__dirname, 'src'), // Add this line to serve from src
        },
      ],// Serve content from the dist directory
    port: 3000, // Port for the Webpack dev server
    open: true, // Open the browser automatically
  },
};
